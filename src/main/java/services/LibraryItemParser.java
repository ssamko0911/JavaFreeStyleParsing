package services;

import entities.libraryItems.LibraryItem;

public interface LibraryItemParser<T extends LibraryItem> {
    T createRecord();
    boolean isFieldLabel(String line);
    void setField(T record, String field, String value);
}
