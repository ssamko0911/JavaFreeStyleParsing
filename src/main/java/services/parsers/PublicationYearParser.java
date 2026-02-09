package services.parsers;

import enums.ErrorLabel;

public class PublicationYearParser {
    private final static String PUBLICATION_YEAR = "Year of publication:";

    public int parsePublicationYear(String publicationYearLine) {
        try {
            return Integer.parseInt(publicationYearLine);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                    String.format(ErrorLabel.PUBLICATION_YEAR_INVALID_VALUE.getLabel(), PublicationYearParser.PUBLICATION_YEAR, publicationYearLine)
            );
        }
    }
}
