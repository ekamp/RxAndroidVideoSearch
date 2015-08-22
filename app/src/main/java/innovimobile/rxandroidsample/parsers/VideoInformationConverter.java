package innovimobile.rxandroidsample.parsers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import innovimobile.rxandroidsample.model.VideoInformation;

/**
 * Converter / Parser used parse the Json video information through the use of Gson.
 *
 * @author Erik Kamp
 * @since 08/21/2015
 */
public class VideoInformationConverter implements JsonDeserializer<VideoInformation> {

    private static final String TAG_TITLE = "Title", TAG_RUNTIME = "Runtime",
            TAG_PLOT = "Plot", TAG_MOVIE_RATING = "Rated", TAG_POSTER_IMAGE_URL = "Poster",
            TAG_CRITIC_RATING = "imdbRating";

    @Override
    public VideoInformation deserialize(JsonElement jsonRootElement, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        VideoInformation videoInformation = new VideoInformation();
        JsonObject jsonRootObject = jsonRootElement.getAsJsonObject();

        videoInformation.setTitle(jsonRootObject.get(TAG_TITLE).getAsString());
        videoInformation.setRuntime(jsonRootObject.get(TAG_RUNTIME).getAsString());
        videoInformation.setDescription(jsonRootObject.get(TAG_PLOT).getAsString());
        videoInformation.setRating(jsonRootObject.get(TAG_MOVIE_RATING).getAsString());
        videoInformation.setPosterImagePath(jsonRootObject.get(TAG_POSTER_IMAGE_URL).getAsString());
        videoInformation.setCriticRating(jsonRootObject.get(TAG_CRITIC_RATING).getAsString());

        return videoInformation;
    }
}
