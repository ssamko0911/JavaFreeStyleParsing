package entities.validation;

import entities.libraryItems.LibraryBookRecord;
import enums.Severity;

import java.util.ArrayList;
import java.util.List;

public class ValidationResult {
    private final LibraryBookRecord record;
    private final List<ValidationIssue> issues = new ArrayList<>();

    public ValidationResult(LibraryBookRecord record) {
        this.record = record;
    }

    public void addIssue(ValidationIssue issue) {
        this.issues.add(issue);
    }

    public boolean hasIssues() {
        return !this.issues.isEmpty();
    }

    public boolean hasErrors() {
        return this.issues.stream().anyMatch(issue -> issue.getSeverity() == Severity.ERROR);
    }

    public LibraryBookRecord getRecord() {
        return this.record;
    }

    public List<ValidationIssue> getIssues() {
        return this.issues;
    }
}
