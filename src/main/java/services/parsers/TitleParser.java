package services.parsers;

import entities.Title;
import util.AppLogger;

public class TitleParser {
    private final static String PATTERN = ":";
    private static final AppLogger logger = AppLogger.getInstance();

    public Title parseTitle(String titleLine) {
        if (titleLine == null || titleLine.isEmpty()) {
            TitleParser.logger.warn("TitleLine is null or empty: " + titleLine);
            return new Title(null, null);
        }

        String[] titleParts = titleLine.split(TitleParser.PATTERN);

        Title title;
        if (titleParts.length == 1) {
            title = new Title(titleParts[0], null);
        } else {
            title = new Title(titleParts[0], titleParts[1]);
        }

        TitleParser.logger.info("Title parsed: " + title);

        return title;
    }
}
