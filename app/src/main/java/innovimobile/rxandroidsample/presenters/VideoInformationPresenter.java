package innovimobile.rxandroidsample.presenters;

import android.util.Log;

import innovimobile.rxandroidsample.model.VideoInformation;
import innovimobile.rxandroidsample.services.VideoQueryService;
import innovimobile.rxandroidsample.views.VideoInformationActivity;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Communication between out view (Activity) and our model.
 *
 * @author Erik Kamp
 * @since 08/21/2015
 */
public class VideoInformationPresenter {

    private VideoInformationActivity videoInformationActivity;
    private VideoQueryService videoQueryService;

    public VideoInformationPresenter(VideoInformationActivity videoInformationActivity, VideoQueryService videoQueryService) {
        this.videoInformationActivity = videoInformationActivity;
        this.videoQueryService = videoQueryService;
    }

    /**
     * Sets the Subscriber(Main Thread) through the use of the built in Schedulers class, in addition to
     * the Observer (the request retrieves from Retrofit).
     *
     * @param videoTitle title to be searched.
     */
    public void loadVideoInformationForTitle(String videoTitle) {
        videoQueryService.getVideoServiceAPI().getVideoInformation(videoTitle).
                subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<VideoInformation>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                videoInformationActivity.displayError();
                Log.e(getClass().getName(), "ERROR " + e.toString());
            }

            @Override
            public void onNext(VideoInformation videoInformation) {
                videoInformationActivity.displayVideoInformation(videoInformation);
            }
        });
    }
}


