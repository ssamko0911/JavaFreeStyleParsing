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
        if (isbnArray.length == 1 && isbnArray[0].length() == IsbnParser.ISBN_13_LENGTH) {
            isbnNumber = new IsbnNumber(isbnArray[0], null);
        } else if (isbnArray.length == 1 && isbnArray[0].length() == IsbnParser.ISBN_10_LENGTH) {
            isbnNumber = new IsbnNumber(null, isbnArray[0]);
        } else if (isbnArray.length == 2 && this.validateIsbnLength(isbnArray)) {
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
}
