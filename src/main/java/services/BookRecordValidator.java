package services;

import entities.LibraryBookRecord;
import entities.ValidationIssue;
import entities.ValidationResult;
import enums.Severity;

import java.util.ArrayList;
import java.util.List;

public class BookRecordValidator {
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

    private ValidationResult validateRecord(LibraryBookRecord record) {
        ValidationResult result = new ValidationResult(record);

        if (this.isBlanc(record.getOclcNumber())) {
            result.addIssue(new ValidationIssue("oclcNumber", "Missing OCLC Number", Severity.ERROR));
        }

        if (this.isBlanc(record.getIsbn())) {
            result.addIssue(new ValidationIssue("isbn", "Missing ISBN", Severity.ERROR));
        }

        if (record.getAuthorsAsString() == null || record.getAuthorsAsString().isEmpty()) {
            result.addIssue(new ValidationIssue("authors", "No authors listed", Severity.WARNING));
        }

        if (record.getTitle() == null || record.getTitle().isEmpty()) {
            result.addIssue(new ValidationIssue("title", "No title", Severity.WARNING));
        }

        if (record.getPublisher() == null || record.getPublisher().isEmpty()) {
            result.addIssue(new ValidationIssue("publisher", "No publisher", Severity.WARNING));
        }

        //TODO: implement year validation or ISBN check-sum validation

        return result;
    }
}
