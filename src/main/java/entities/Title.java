package entities;

public class Title {
    private final String mainTitle;
    private final String subTitle;

    public Title(String mainTitle, String subTitle) {
        this.mainTitle = mainTitle;
        this.subTitle = subTitle;
    }

    public String getMainTitle() {
        return this.mainTitle;
    }

    public String getSubTitle() {
        return this.subTitle;
    }

    @Override
    public String toString() {
        if (this.subTitle == null || this.subTitle.isEmpty()) {
            return mainTitle;
        }

        return String.format("%s : %s", this.mainTitle, this.subTitle);
    }
}
