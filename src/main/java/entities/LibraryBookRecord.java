package entities;

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

    @Override
    public String toString() {
        return "LibraryBookRecord{" + System.lineSeparator() +
                "oclcNumber='" + oclcNumber + '\'' + System.lineSeparator() +
                "title='" + title + '\'' + System.lineSeparator() +
                "genre='" + genre + '\'' + System.lineSeparator() +
                "author='" + author + '\'' + System.lineSeparator() +
                "summary='" + summary + '\'' + System.lineSeparator() +
                "publicationYear='" + publicationYear + '\'' + System.lineSeparator() +
                "publisher='" + publisher + '\'' + System.lineSeparator() +
                "physicalDescription='" + physicalDescription + '\'' + System.lineSeparator() +
                "isbn='" + isbn + '\'' + System.lineSeparator() +
                '}';
    }
}
