package innovimobile.rxandroidsample.model;

/**
 * Contains relevant information related to the information downloaded for a video.
 *
 * @author Erik Kamp
 * @since 08/21/2015
 */
public class VideoInformation {

    private String title, runtime, rating, description, criticRating, posterImagePath;

    public VideoInformation() {
    }

    public String getTitle() {
        return title;
    }

    public String getRuntime() {
        return runtime;
    }

    public String getContentRating() {
        return rating;
    }

    public String getDescription() {
        return description;
    }

    public String getCriticRating() {
        return criticRating;
    }

    public String getPosterImagePath() {
        return posterImagePath;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCriticRating(String criticRating) {
        this.criticRating = criticRating;
    }

    public void setPosterImagePath(String posterImagePath) {
        this.posterImagePath = posterImagePath;
    }

    @Override
    public String toString() {
        return "VideoInformation{" +
                "title='" + title + '\'' +
                ", runtime='" + runtime + '\'' +
                ", rating='" + rating + '\'' +
                ", description='" + description + '\'' +
                ", criticRating='" + criticRating + '\'' +
                ", posterImagePath='" + posterImagePath + '\'' +
                '}';
    }
}
