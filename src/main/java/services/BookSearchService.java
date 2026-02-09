package services;

import entities.libraryItems.LibraryBookRecord;
import entities.search.SearchCriteria;

import java.util.List;
import java.util.stream.Collectors;

public class BookSearchService {
    public List<LibraryBookRecord> searchBooks(List<LibraryBookRecord> records, SearchCriteria searchCriteria) {
        return records.stream()
                .filter(record -> matches(record, searchCriteria))
                .collect(Collectors.toList());
    }

    private boolean matches(LibraryBookRecord record, SearchCriteria criteria) {
        String query = criteria.isCaseSensitive() ? criteria.getQuery() : criteria.getQuery().toLowerCase();

        return switch (criteria.getField()) {
            case OCLC -> this.contains(record.getOclcNumber(), query, criteria.isCaseSensitive());
            case TITLE -> this.contains(record.getTitleAsString(), query, criteria.isCaseSensitive());
            case AUTHOR -> this.contains(record.getAuthorsAsString(), query, criteria.isCaseSensitive());
            case YEAR -> String.valueOf(record.getPublicationYear()).contains(query);
            case PUBLISHER -> this.contains(record.getPublisherAsString(), query, criteria.isCaseSensitive());
            case GENRE -> this.contains(record.getGenre(), query, criteria.isCaseSensitive());
            case ISBN -> this.contains(record.getIsbnAsString(), query, criteria.isCaseSensitive());
            case ALL -> this.matchesAny(record, query, criteria.isCaseSensitive());
        };
    }

    private boolean contains(String value, String query, boolean caseSensitive) {
        if (value == null) {
            return false;
        }

        return caseSensitive ? value.contains(query) : value.toLowerCase().contains(query);
    }

    private boolean matchesAny(LibraryBookRecord record, String query, boolean caseSensitive) {
        return contains(record.getTitleAsString(), query, caseSensitive) ||
                contains(record.getAuthorsAsString(), query, caseSensitive) ||
                contains(record.getIsbnAsString(), query, caseSensitive) ||
                contains(record.getOclcNumber(), query, caseSensitive) ||
                contains(record.getGenre(), query, caseSensitive) ||
                contains(record.getPublisherAsString(), query, caseSensitive) ||
                String.valueOf(record.getPublicationYear()).contains(query);
    }
}
