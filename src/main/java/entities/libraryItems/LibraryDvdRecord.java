package entities.libraryItems;

import enums.BookRecordFormat;

public class LibraryDvdRecord extends LibraryItem{
    private String cast;
    private String credits;
    private String plot;
    private int releaseYear;
    private String language;
    private String publisher;
    private String physicalDescription;

    public String getCast() {
        return cast;
    }

    public LibraryDvdRecord setCast(String cast) {
        this.cast = cast;
        return this;
    }

    public String getCredits() {
        return credits;
    }

    public LibraryDvdRecord setCredits(String credits) {
        this.credits = credits;
        return this;
    }

    public String getPlot() {
        return plot;
    }

    public LibraryDvdRecord setPlot(String plot) {
        this.plot = plot;
        return this;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public LibraryDvdRecord setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
        return this;
    }

    public String getLanguage() {
        return language;
    }

    public LibraryDvdRecord setLanguage(String language) {
        this.language = language;
        return this;
    }

    public String getPublisher() {
        return publisher;
    }

    public LibraryDvdRecord setPublisher(String publisher) {
        this.publisher = publisher;
        return this;
    }

    public String getPhysicalDescription() {
        return physicalDescription;
    }

    public LibraryDvdRecord setPhysicalDescription(String physicalDescription) {
        this.physicalDescription = physicalDescription;
        return this;
    }

    @Override
    public String toString() {
        return BookRecordFormat.TO_STRING.getLabel().formatted(
                this.getOclcNumber(),
                this.getTitle(),
                this.getCast(),
                this.getCredits(),
                this.getPlot(),
                this.getReleaseYear(),
                this.getPublisher(),
                this.getLanguage(),
                this.getGenre(),
                this.getPhysicalDescription(),
                this.getIsbnAsString()
        );
    }
}
