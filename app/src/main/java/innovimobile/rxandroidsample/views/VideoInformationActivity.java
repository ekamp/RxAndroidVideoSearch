package innovimobile.rxandroidsample.views;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import innovimobile.rxandroidsample.R;
import innovimobile.rxandroidsample.model.VideoInformation;
import innovimobile.rxandroidsample.presenters.VideoInformationPresenter;
import innovimobile.rxandroidsample.services.VideoQueryService;

/**
 * Activity utilized to display a searchable EditText View in order to allow a user to search a video,
 * and view information on such video within the newly rendered video information display.
 *
 * @author Erik Kamp
 * @since 08/21/2015
 */
public class VideoInformationActivity extends AppCompatActivity {

    private VideoQueryService videoQueryService;
    private VideoInformationPresenter videoInformationPresenter;

    @Bind(R.id.content_viewgroup)
    RelativeLayout contentViewGroup;

    @Bind(R.id.video_poster)
    ImageView videoPoster;

    @Bind(R.id.video_title)
    TextView videoTitle;

    @Bind(R.id.video_content_rating)
    TextView videoContentRating;

    @Bind(R.id.video_runtime)
    TextView videoRuntime;

    @Bind(R.id.video_critic_rating)
    TextView videoCriticRating;

    @Bind(R.id.video_description)
    TextView videoDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_controller);
        ButterKnife.bind(this);
        instanciateQueryService();
        handleSearchableIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleSearchableIntent(intent);
    }

    /**
     * Creates the video title search service enabling the presenter to bind the service with the current view
     * so that the view may receive a callback when the service completes.
     */
    private void instanciateQueryService() {
        videoQueryService = new VideoQueryService();
        videoInformationPresenter = new VideoInformationPresenter(this, videoQueryService);
    }

    /**
     * Filters incoming intents looking for the action search intent which is broadcast when a user
     * is done searching in the search action area of the Toolbar/Actionbar
     *
     * @param intent incoming intent.
     */
    private void handleSearchableIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String videoTitleQuery = intent.getStringExtra(SearchManager.QUERY);
            videoInformationPresenter.loadVideoInformationForTitle(TextUtils.htmlEncode(videoTitleQuery));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.video_information_search_menu, menu);
        createSearchableMenu(menu);
        return true;
    }

    /**
     * Binds the system search service to the video search icon action component of the actionbar,
     * allowing a user to press the search icon in order to look for a movie.
     *
     * @param menu current options menu.
     */
    private void createSearchableMenu(Menu menu) {
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.video_search).getActionView();
        ComponentName cn = new ComponentName(this, VideoInformationActivity.class);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(cn));
    }

    /**
     * Callback used when the video service successfully completes. This will display information
     * collected by the service within the corresponding views.
     *
     * @param videoInformation video information collected by our video query service.
     */
    public void displayVideoInformation(VideoInformation videoInformation) {
        Picasso.with(this).load(videoInformation.getPosterImagePath()).fit().into(videoPoster);
        videoTitle.setText(videoInformation.getTitle());
        videoContentRating.setText(videoInformation.getContentRating());
        videoCriticRating.setText(videoInformation.getCriticRating());
        videoDescription.setText(videoInformation.getDescription());
        videoRuntime.setText(videoInformation.getRuntime());
    }

    /**
     * If the video query service encounters an error a SnackBar is displayed here in order to notify
     * the user.
     */
    public void displayError() {
        Snackbar.make(contentViewGroup, getString(R.string.error_cannot_find_video), Snackbar.LENGTH_SHORT).show();
    }
}