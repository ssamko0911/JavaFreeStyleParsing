package entities.validation;

import enums.Severity;

public class ValidationIssue {
    private final String field;
    private final String message;
    private final Severity severity;

    public ValidationIssue(String field, String message, Severity severity) {
        this.field = field;
        this.message = message;
        this.severity = severity;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }

    public Severity getSeverity() {
        return severity;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s: %s", this.field, this.message, this.severity);
    }
}
