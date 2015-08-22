package innovimobile.rxandroidsample.services;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import innovimobile.rxandroidsample.model.VideoInformation;
import innovimobile.rxandroidsample.parsers.VideoInformationConverter;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Service used to search for a video within the OMDB database.
 *
 * @author Erik Kamp
 * @since 08/21/2015
 */
public class VideoQueryService {
    private static final String VIDEO_INFORMATION_SERVER_BASE_URL = "http://www.omdbapi.com";
    private VideoServiceAPI videoServiceAPI;

    public VideoQueryService() {
        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestInterceptor.RequestFacade request) {
                request.addHeader("Accept", "application/json");
            }
        };

        Gson gsonConverter = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(VideoInformation.class, new VideoInformationConverter())
                .create();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(VIDEO_INFORMATION_SERVER_BASE_URL)
                .setRequestInterceptor(requestInterceptor)
                .setConverter(new GsonConverter(gsonConverter))
                .build();

        videoServiceAPI = restAdapter.create(VideoServiceAPI.class);
    }

    public VideoServiceAPI getVideoServiceAPI() {
        return videoServiceAPI;
    }

    public interface VideoServiceAPI {

        @GET("/")
        public Observable<VideoInformation>
        getVideoInformation(@Query("t") String videoTitle);
    }

}
