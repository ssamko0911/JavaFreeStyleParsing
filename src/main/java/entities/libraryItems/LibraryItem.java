package entities.libraryItems;

import entities.IsbnNumber;
import entities.Title;
import enums.RecordType;

public abstract class LibraryItem {
    private RecordType recordType;
    private String oclcNumber;
    private Title title;
    private String genre;
    private IsbnNumber isbn;

    public LibraryItem() {
    }

    public LibraryItem(RecordType recordType) {
        this.recordType = recordType;
    }

    public LibraryItem(RecordType recordType, String oclcNumber, Title title) {
        this.recordType = recordType;
        this.oclcNumber = oclcNumber;
        this.title = title;
    }

    public RecordType getRecordType() {
        return this.recordType;
    }

    public String getRecordTypeAsString() {
        return this.recordType.toString();
    }

    public String getOclcNumber() {
        return oclcNumber;
    }

    public void setOclcNumber(String oclcNumber) {
        this.oclcNumber = oclcNumber;
    }

    public Title getTitle() {
        return title;
    }

    public String getTitleAsString() {
        return this.title.toString();
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public LibraryItem setGenre(String genre) {
        this.genre = genre;
        return this;
    }

    public IsbnNumber getIsbn() {
        return isbn;
    }

    public String getIsbnAsString() {
        if (this.isbn == null) {
            return "";
        }

        return this.isbn.toString();
    }

    public LibraryItem setIsbn(IsbnNumber isbn) {
        this.isbn = isbn;
        return this;
    }

    public boolean isValid() {
        return this.oclcNumber != null || this.isbn != null;
    }
}
