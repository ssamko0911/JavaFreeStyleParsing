package entities;

public abstract class LibraryItem {
    private String oclcNumber;
    private Title title;

    public LibraryItem() {
    }

    protected String getOclcNumber() {
        return oclcNumber;
    }

    protected void setOclcNumber(String oclcNumber) {
        this.oclcNumber = oclcNumber;
    }

    protected Title getTitle() {
        return title;
    }

    protected void setTitle(Title title) {
        this.title = title;
    }
}
