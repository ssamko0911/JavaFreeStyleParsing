package entities.libraryItems;

import entities.*;
import enums.BookRecordLabel;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class LibraryBookRecord {
    private String oclcNumber;
    private Title title;
    private String genre;
    private List<Author> authors;
    private String summary;
    private int publicationYear;
    private Publisher publisher;
    private PhysicalDescription physicalDescription;
    private IsbnNumber isbn;

    public LibraryBookRecord() {
    }

    public LibraryBookRecord(String oclcNumber, Title title) {
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

    public Title getTitle() {
        return title;
    }

    public String getTitleAsString() {
        return this.title.toString();
    }

    public LibraryBookRecord setTitle(Title title) {
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

    public List<Author> getAuthors() {
        return authors;
    }

    public LibraryBookRecord setAuthors(List<Author> authors) {
        this.authors = authors;
        return this;
    }

    public String getAuthorsAsString() {
        if (this.authors.isEmpty()) {
            return "";
        }

        return this.authors.stream()
                .map(Author::toString)
                .collect(Collectors.joining(", "));
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

    public Publisher getPublisher() {
        return publisher;
    }

    public String getPublisherAsString() {
        if (this.publisher == null) {
            return "";
        }

        return  this.publisher.toString();
    }

    public LibraryBookRecord setPublisher(Publisher publisher) {
        this.publisher = publisher;
        return this;
    }

    public PhysicalDescription getPhysicalDescription() {
        return physicalDescription;
    }

    public String getPhysicalDescriptionAsString() {
        if (this.physicalDescription == null) {
            return "";
        }

        return this.physicalDescription.toString();
    }

    public LibraryBookRecord setPhysicalDescription(PhysicalDescription physicalDescription) {
        this.physicalDescription = physicalDescription;
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

    public LibraryBookRecord setIsbn(IsbnNumber isbn) {
        this.isbn = isbn;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LibraryBookRecord that = (LibraryBookRecord) o;

        return Objects.equals(this.oclcNumber, that.oclcNumber) && Objects.equals(this.isbn, that.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.oclcNumber, this.isbn);
    }

    @Override
    public String toString() {
        return BookRecordLabel.TO_STRING.getLabel().formatted(
                this.oclcNumber,
                this.title,
                this.getAuthorsAsString(),
                this.summary,
                this.publicationYear,
                this.getPublisherAsString(),
                this.genre,
                this.getPhysicalDescriptionAsString(),
                this.getIsbnAsString()
        );
    }

    public boolean isValid() {
        return this.oclcNumber != null || this.isbn != null;
    }
}
