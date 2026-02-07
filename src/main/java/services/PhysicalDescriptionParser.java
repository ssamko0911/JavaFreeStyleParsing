package services;

import entities.PhysicalDescription;
import enums.BookRecordLabel;
import util.AppLogger;

public class PhysicalDescriptionParser {
    private final static String PATTERN = ";";
    private static final AppLogger logger = AppLogger.getInstance();

    public PhysicalDescription parsePhysicalDescription(String physicalDescriptionLine) {
        if (physicalDescriptionLine == null || physicalDescriptionLine.isEmpty()) {
            return new PhysicalDescription(null, null);
        }

        String[] physicalDescriptionArray = physicalDescriptionLine.split(PATTERN);
        PhysicalDescription physicalDescription = new PhysicalDescription(
                physicalDescriptionArray[0].trim(),
                physicalDescriptionArray.length > 1 ? physicalDescriptionArray[1].trim() : null
        );

        PhysicalDescriptionParser.logger.info(
                String.format(BookRecordLabel.PHYSICAL_DESCRIPTION_PARSED.getLabel(), physicalDescription));

        return physicalDescription;
    }
}

