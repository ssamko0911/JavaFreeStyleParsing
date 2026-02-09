package services;

import entities.libraryItems.LibraryBookRecord;
import entities.validation.ValidationIssue;
import entities.validation.ValidationResult;
import enums.Severity;

import java.util.ArrayList;
import java.util.List;

public class BookRecordValidator {
    private static final int CURRENT_YEAR = java.time.LocalDate.now().getYear();

    public List<ValidationResult> validate(List<LibraryBookRecord> records) {
        List<ValidationResult> results = new ArrayList<>();

        for (LibraryBookRecord record : records) {
            ValidationResult result = this.validateRecord(record);
            if (result.hasIssues()) {
                results.add(result);
            }
        }

        return results;
    }

    private boolean isBlanc(String value) {
        return value == null || value.isBlank();
    }

    //TODO: move labels to enums;
    private ValidationResult validateRecord(LibraryBookRecord record) {
        ValidationResult result = new ValidationResult(record);

        if (this.isBlanc(record.getOclcNumber())) {
            result.addIssue(new ValidationIssue("oclcNumber", "Missing OCLC Number", Severity.ERROR));
        }

        if (this.isBlanc(record.getIsbnAsString())) {
            result.addIssue(new ValidationIssue("isbn", "Missing ISBN", Severity.ERROR));
        }

        if (record.getTitle() == null || record.getTitleAsString().isEmpty()) {
            result.addIssue(new ValidationIssue("title", "No title", Severity.WARNING));
        }

        if (record.getAuthorsAsString() == null || record.getAuthorsAsString().isEmpty()) {
            result.addIssue(new ValidationIssue("authors", "No authors listed", Severity.WARNING));
        }

        if (record.getSummary() == null || record.getSummary().isEmpty()) {
            result.addIssue(new ValidationIssue("summary", "No summary", Severity.WARNING));
        }

        if (record.getPublicationYear() == 0) {
            result.addIssue(new ValidationIssue("publicationYear", "No publication year", Severity.WARNING));
        } else if (record.getPublicationYear() > BookRecordValidator.CURRENT_YEAR) {
            result.addIssue(new ValidationIssue("publicationYear", "Invalid year: " + record.getPublicationYear(), Severity.WARNING));
        }

        if (record.getPublisherAsString() == null || record.getPublisherAsString().isEmpty()) {
            result.addIssue(new ValidationIssue("publisher", "No publisher", Severity.WARNING));
        }

        if (record.getGenre() == null || record.getGenre().isEmpty()) {
            result.addIssue(new ValidationIssue("genre", "No genre", Severity.WARNING));
        }

        if (record.getPhysicalDescriptionAsString() == null || record.getPhysicalDescriptionAsString().isEmpty()) {
            result.addIssue(new ValidationIssue("physicalDescription", "No physicalDescription", Severity.WARNING));
        }

        return result;
    }
}
