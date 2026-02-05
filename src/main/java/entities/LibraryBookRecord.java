package entities;

public class LibraryBookRecord {
    private String oclcNumber;
    private String title;
    private String genre;
    private String author;
    private String summary;
    private String publicationYear;

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

    public String getPublicationYear() {
        return publicationYear;
    }

    public LibraryBookRecord setPublicationYear(String publicationYear) {
        this.publicationYear = publicationYear;
        return this;
    }

    @Override
    public String toString() {
        return "LibraryBookRecord{" +
                "oclcNumber='" + oclcNumber + '\'' +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", publicationYear=" + publicationYear +
                '}';
    }
}
