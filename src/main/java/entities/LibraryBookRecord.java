package entities;

import enums.BookRecordLabel;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class LibraryBookRecord {
    private String oclcNumber;
    private String title;
    private String genre;
    private List<Author> authors;
    private String summary;
    private int publicationYear;
    private String publisher;
    private PhysicalDescription physicalDescription;
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

    public String getPublisher() {
        return publisher;
    }

    public LibraryBookRecord setPublisher(String publisher) {
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

    public String getIsbn() {
        return isbn;
    }

    public LibraryBookRecord setIsbn(String isbn) {
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
                this.publisher,
                this.genre,
                this.getPhysicalDescriptionAsString(),
                this.isbn
        );
    }

    public boolean isValid() {
        return this.oclcNumber != null || this.isbn != null;
    }
}
