package entities;

import java.util.Objects;

public class PhysicalDescription {
    private String pagesInfo;
    private String dimensions;

    public PhysicalDescription(String pagesInfo, String dimensions) {
        this.pagesInfo = pagesInfo;
        this.dimensions = dimensions;
    }

    public String getPagesInfo() {
        return this.pagesInfo;
    }

    public PhysicalDescription setPagesInfo(String pagesInfo) {
        this.pagesInfo = pagesInfo;
        return this;
    }

    public String getDimensions() {
        return this.dimensions;
    }

    public PhysicalDescription setDimensions(String dimensions) {
        this.dimensions = dimensions;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PhysicalDescription that = (PhysicalDescription) o;

        return Objects.equals(pagesInfo, that.pagesInfo) && Objects.equals(dimensions, that.dimensions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pagesInfo, dimensions);
    }

    @Override
    public String toString() {
        if (this.dimensions == null || this.dimensions.isEmpty()) {
            return pagesInfo;
        }

        return this.pagesInfo + " ; " + this.dimensions;
    }
}
