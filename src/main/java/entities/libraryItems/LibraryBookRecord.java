package entities.libraryItems;

import entities.*;
import enums.BookRecordFormat;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class LibraryBookRecord extends LibraryItem {
    private List<Author> authors;
    private String summary;
    private int publicationYear;
    private Publisher publisher;
    private PhysicalDescription physicalDescription;

    public LibraryBookRecord() {
        super();
    }

    public LibraryBookRecord(String oclcNumber, Title title) {
        super(oclcNumber, title);
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LibraryBookRecord that = (LibraryBookRecord) o;

        return Objects.equals(this.getOclcNumber(), that.getOclcNumber()) && Objects.equals(this.getIsbn(), that.getIsbn());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getOclcNumber(), this.getIsbn());
    }

    @Override
    public String toString() {
        return BookRecordFormat.TO_STRING.getLabel().formatted(
                this.getOclcNumber(),
                this.getTitle(),
                this.getAuthorsAsString(),
                this.summary,
                this.publicationYear,
                this.getPublisherAsString(),
                this.getGenre(),
                this.getPhysicalDescriptionAsString(),
                this.getIsbnAsString()
        );
    }

    public boolean isValid() {
        return this.getOclcNumber() != null || this.getIsbn() != null;
    }
}
