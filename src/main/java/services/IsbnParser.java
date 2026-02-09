package services;

import entities.IsbnNumber;
import util.AppLogger;

public class IsbnParser {
    private final static String PATTERN = ", ";
    private static final AppLogger logger = AppLogger.getInstance();

    public IsbnNumber parseIsbn(String isbnLine) {
        if (isbnLine == null || isbnLine.isEmpty()) {
            IsbnParser.logger.warn("Failed to parse isbn: " + isbnLine);

            return new IsbnNumber(null, null);
        }

        String[] isbnArray = isbnLine.split(PATTERN);

        IsbnNumber isbnNumber;
        if (isbnArray.length == 1) {
            isbnNumber = new IsbnNumber(isbnArray[0], null);
        } else {
            isbnNumber = new IsbnNumber(isbnArray[0], isbnArray[1]);
        }


        IsbnParser.logger.info("ISBN parsed: " + isbnNumber);

        return isbnNumber;
    }
}
