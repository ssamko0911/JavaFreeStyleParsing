package entities;

import enums.BookRecordLabel;

import java.util.Objects;

public class LibraryBookRecord {
    private String oclcNumber;
    private String title;
    private String genre;
    private String author;
    private String summary;
    private int publicationYear;
    private String publisher;
    private String physicalDescription;
    private String isbn;

    public LibraryBookRecord() {
    }

    public LibraryBookRecord(String oclcNumber, String title) {
        this.oclcNumber = oclcNumber;
        this.title = title;
    }

    public String getOclcNumber() {
        return oclcNumber;
    }

    public LibraryBookRecord setOclcNumber(String oclcNumber) {
        this.oclcNumber = oclcNumber;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public LibraryBookRecord setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getGenre() {
        return genre;
    }

    public LibraryBookRecord setGenre(String genre) {
        this.genre = genre;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public LibraryBookRecord setAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getSummary() {
        return summary;
    }

    public LibraryBookRecord setSummary(String summary) {
        this.summary = summary;
        return this;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public LibraryBookRecord setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
        return this;
    }

    public String getPublisher() {
        return publisher;
    }

    public LibraryBookRecord setPublisher(String publisher) {
        this.publisher = publisher;
        return this;
    }

    public String getPhysicalDescription() {
        return physicalDescription;
    }

    public LibraryBookRecord setPhysicalDescription(String physicalDescription) {
        this.physicalDescription = physicalDescription;
        return this;
    }

    public String getIsbn() {
        return isbn;
    }

    public LibraryBookRecord setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    //TODO: Rewrite when the OCLC Number updated to be unique;
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        LibraryBookRecord that = (LibraryBookRecord) o;
        return Objects.equals(isbn, that.isbn);
    }

    @Override
    public int hashCode() {
        // method hash() for multiple params;
        return Objects.hashCode(isbn);
    }

    @Override
    public String toString() {
        return BookRecordLabel.TO_STRING.getLabel().formatted(
                this.oclcNumber,
                this.title,
                this.author,
                this.summary,
                this.publicationYear,
                this.publisher,
                this.genre,
                this.physicalDescription,
                this.isbn
        );
    }
}
