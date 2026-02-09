package entities;

public class IsbnNumber {
    public final String isbn13;
    public final String isbn10;


    public IsbnNumber(String isbn13, String isbn10) {
        this.isbn13 = isbn13;
        this.isbn10 = isbn10;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public String getIsbn10() {
        return isbn10;
    }

    @Override
    public String toString() {
        if (this.isbn10 == null || this.isbn10.isEmpty()) {
            return this.isbn13;
        }

        if (this.isbn13 == null || this.isbn13.isEmpty()) {
            return this.isbn10;
        }

        return String.format("%s, %s", this.isbn13, this.isbn10);
    }
}
