package entities.search;

import enums.SearchField;

public class SearchCriteria {
    private final SearchField field;
    private final String query;
    private final boolean caseSensitive;

    public SearchCriteria(SearchField field, String query, boolean caseSensitive) {
        this.field = field;
        this.query = query;
        this.caseSensitive = caseSensitive;
    }

    public SearchField getField() {
        return field;
    }

    public String getQuery() {
        return query;
    }

    public boolean isCaseSensitive() {
        return caseSensitive;
    }
}
