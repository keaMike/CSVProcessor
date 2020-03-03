public class MovieInfo {

    private String actorOne;
    private String title;
    private int titleYear;
    private double rating;

    public MovieInfo() {
    }

    public MovieInfo(String actorOne, String title, int titleYear, double rating) {
        this.actorOne = actorOne;
        this.title = title;
        this.titleYear = titleYear;
        this.rating = rating;
    }

    public String getActorOne() {
        return actorOne;
    }

    public void setActorOne(String actorOne) {
        this.actorOne = actorOne;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTitleYear() {
        return titleYear;
    }

    public void setTitleYear(int titleYear) {
        this.titleYear = titleYear;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "--------------------------------------------\n" +
                "Actor: " + actorOne + "\n" +
                "Title: " + title + "\n" +
                "Title Year: " + titleYear + "\n" +
                "Rating: " + rating;
    }
}
