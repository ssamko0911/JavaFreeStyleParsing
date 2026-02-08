package services;

import entities.Publisher;
import enums.ErrorLabel;
import enums.LogLabel;
import util.AppLogger;

import java.util.ArrayList;
import java.util.List;

public class PublisherParser {
    private final static String PATTERN = ",\\s*";
    private final static String JOIN = ", ";
    private static final AppLogger logger = AppLogger.getInstance();

    public Publisher parsePublisher(String publisherLine) {
        if (publisherLine == null || publisherLine.isEmpty()) {
            PublisherParser.logger.warn(ErrorLabel.PARSE_ERROR_YEAR.getLabel() + publisherLine);
            return new Publisher(null, null, 0);
        }

        List<String> publisherList = new ArrayList<>(List.of(publisherLine.split(PATTERN)));
        String name = publisherList.removeFirst();

        try {
            int year = Integer.parseInt(publisherList.removeLast());

            String location = String.join(PublisherParser.JOIN, publisherList);
            Publisher publisher = new Publisher(
                    name, location, year
            );

            PublisherParser.logger.info(String.format(LogLabel.PARSED_PUBLISHER.getLabel(), publisher));

            return publisher;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                    String.format(ErrorLabel.PUBLICATION_YEAR_INVALID_VALUE.getLabel(), LogLabel.YEAR_LOG.getLabel(), publisherLine)
            );
        }
    }
}
