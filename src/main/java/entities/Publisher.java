package entities;

public class Publisher {
    private final String name;
    private final String location;
    private final int year;

    public Publisher(String name, String location, int year) {
        this.name = name;
        this.location = location;
        this.year = year;
    }

    public String getName() {
        return this.name;
    }

    public String getLocation() {
        return this.location;
    }

    public int getYear() {
        return this.year;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %d", this.name, this.location, this.year);
    }
}
