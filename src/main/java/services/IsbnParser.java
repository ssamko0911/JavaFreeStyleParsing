package services;

import entities.IsbnNumber;
import util.AppLogger;

public class IsbnParser {
    private final static String PATTERN = ", ";
    private final static int ISBN_13_LENGTH = 13;
    private final static int ISBN_10_LENGTH = 10;
    private static final AppLogger logger = AppLogger.getInstance();

    public IsbnNumber parseIsbn(String isbnLine) {
        if (isbnLine == null || isbnLine.isEmpty()) {
            IsbnParser.logger.warn("Failed to parse isbn: " + isbnLine);

            return new IsbnNumber(null, null);
        }

        String[] isbnArray = isbnLine.split(PATTERN);

        IsbnNumber isbnNumber;
        if (isbnArray.length == 1 && isbnArray[0].length() == IsbnParser.ISBN_13_LENGTH && this.isValidIsbn13(isbnArray[0])) {
            isbnNumber = new IsbnNumber(isbnArray[0], null);
        } else if (isbnArray.length == 1 && isbnArray[0].length() == IsbnParser.ISBN_10_LENGTH && this.isValidIsbn10(isbnArray[0])) {
            isbnNumber = new IsbnNumber(null, isbnArray[0]);
        } else if (isbnArray.length == 2 && this.validateIsbnLength(isbnArray) && this.isValidIsbn13(isbnArray[0]) && this.isValidIsbn10(isbnArray[1])) {
            isbnNumber = new IsbnNumber(isbnArray[0], isbnArray[1]);
        } else {
            IsbnParser.logger.warn("Failed to parse isbn: " + isbnLine);

            return new IsbnNumber(null, null);
        }

        IsbnParser.logger.info("ISBN parsed: " + isbnNumber);

        return isbnNumber;
    }

    private boolean validateIsbnLength(String[] isbnArray) {
        return isbnArray[0].length() == IsbnParser.ISBN_13_LENGTH &&
                isbnArray[1].length() == IsbnParser.ISBN_10_LENGTH;
    }

    private boolean isValidIsbn13(String isbn13) {
        int sum = 0;
        for (int i = 0; i < IsbnParser.ISBN_13_LENGTH; i++) {
            int digit = isbn13.charAt(i) - '0';
            sum += (i % 2 == 0) ? digit : digit * 3;
        }

        return sum % 10 == 0;
    }

    private boolean isValidIsbn10(String isbn10) {
        int sum = 0;
        for (int i = 0; i < IsbnParser.ISBN_10_LENGTH; i++) {
            int value = (i == 9 && isbn10.charAt(i) == 'X') ? 10 : isbn10.charAt(i) - '0';
            sum += value * (i + 1);
        }

        return sum % 11 == 0;
    }
}
