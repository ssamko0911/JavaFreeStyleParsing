package entities;

public class LibraryBookRecord {
    private String oclcNumber;
    private String title;

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

    @Override
    public String toString() {
        return "LibraryBookRecord{" +
                "oclcNumber='" + oclcNumber + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
