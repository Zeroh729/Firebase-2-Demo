package zeroh729.firebase2demo.usecases;

import android.content.Intent;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.widget.TextView;

import com.getbase.floatingactionbutton.FloatingActionsMenu;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OnActivityResult;
import org.androidannotations.annotations.Touch;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import zeroh729.firebase2demo.R;
import zeroh729.firebase2demo.models.Shoutout;
import zeroh729.firebase2demo.models.User;
import zeroh729.firebase2demo.usecases.authentication.AuthenticationDialog;
import zeroh729.firebase2demo.usecases.contentsubscriber.ContentPresenter;
import zeroh729.firebase2demo.usecases.contentsubscriber.interfaces.ContentSubScreenInterface;
import zeroh729.firebase2demo.usecases.minoritycontest.ContestPresenter;
import zeroh729.firebase2demo.usecases.minoritycontest.ContestScreen;
import zeroh729.firebase2demo.usecases.minoritycontest.QnaScreen;
import zeroh729.firebase2demo.usecases.minoritycontest.ResultsScreen;
import zeroh729.firebase2demo.usecases.navigation.NavigationPresenter;
import zeroh729.firebase2demo.usecases.navigation.interfaces.NavigationScreenInterface;
import zeroh729.firebase2demo.usecases.networksubscriber.NetworkPresenter;
import zeroh729.firebase2demo.usecases.networksubscriber.interfaces.NetworkSubScreenInterface;
import zeroh729.firebase2demo.usecases.sendfeedback.SendFeedbackScreen;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements NetworkSubScreenInterface, ContentSubScreenInterface, NavigationScreenInterface{
    private NetworkPresenter networkPresenter;
    private ContentPresenter contentPresenter;
    private NavigationPresenter navigationPresenter;
    private AuthenticationDialog authenticationDialog;
    private ContestPresenter contestPresenter;
    private ContestScreen contestScreen;
    private SendFeedbackScreen sendFeedbackScreen;

    @ViewById(R.id.tv_status)
    TextView tv_status;

    @ViewById(R.id.fab_menu)
    FloatingActionsMenu fab_menu;

    @AfterViews
    public void afterViews(){
        networkPresenter = new NetworkPresenter(this);
        contentPresenter = new ContentPresenter(this);
        navigationPresenter = new NavigationPresenter(this);
        authenticationDialog = new AuthenticationDialog(this);
        sendFeedbackScreen = new SendFeedbackScreen();
        contestScreen = new ContestScreen();
        contestPresenter = new ContestPresenter(contestScreen);
        contestScreen.setQnaScreen(contestPresenter, (QnaScreen)findViewById(R.id.layout_contest_qna));
        contestScreen.setResultsScreen(contestPresenter, (ResultsScreen)findViewById(R.id.layout_contest_results));
        fab_menu.setOnFloatingActionsMenuUpdateListener(new FloatingActionsMenu.OnFloatingActionsMenuUpdateListener() {
            @Override
            public void onMenuExpanded() {
                onClickFabMenu();
            }

            @Override
            public void onMenuCollapsed() {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        networkPresenter.subscribeToNetworkState();
        contentPresenter.subscribeToShoutouts();
        contestPresenter.subscribeToContest();
    }

    @Override
    protected void onPause() {
        networkPresenter.unsubscribe();
        super.onPause();
    }

    public void onClickFabMenu(){
        navigationPresenter.onClickMenu();
    }

    @Click(R.id.fab_rate)
    public void onClickFabRate(){
        navigationPresenter.onClickRating();
    }

    @Click(R.id.fab_question)
    public void onClickFabQuestion(){
        navigationPresenter.onClickQuestion();
    }

    @Click(R.id.fab_shoutout)
    public void onClickFabShoutout(){
        navigationPresenter.onClickShoutout();
    }

    @Override
    public void showError(int errorCode) {

    }

    @Override
    public void showNewShoutout(Shoutout shoutOuts) {
        tv_status.setText(shoutOuts.getMessage());
        sleepThenClearStatus();
    }

    @Background
    public void sleepThenClearStatus() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        clearStatus();
    }

    @UiThread
    public void clearStatus(){
        tv_status.setText("");
    }

    @Override
    public boolean isStatusbarIsEmpty() {
        return tv_status.getText().toString().isEmpty();
    }

    @Override
    public void displayConnectedStatus() {
        tv_status.setText("Connected");
        tv_status.setBackgroundResource(android.R.color.holo_green_dark);
        sleepThenClearStatus();
    }

    @Override
    public void displayConnectingStatus() {
        tv_status.setText("Connecting...");
        tv_status.setBackgroundResource(android.R.color.holo_orange_light);
    }

    @Override
    public void displayDisconnectedStatus() {
        tv_status.setText("Disconnected");
        tv_status.setBackgroundResource(android.R.color.holo_red_dark);
    }

    @Override
    public void displayQuestionScreen(User user) {
        sendFeedbackScreen.showQuestionDialog(this, user);
    }

    @Override
    public void displayMenuList() {

    }

    @Override
    public void displayRatingScreen() {
        sendFeedbackScreen.showRatingDialog(this);
    }

    @Override
    public void displayShoutoutScreen() {
        sendFeedbackScreen.showShoutoutDialog(this);
    }

    @Override
    public void displayLogin() {
        authenticationDialog.show();
        fab_menu.collapse();
    }

    @OnActivityResult(9001)
    public void onResult(int resultCode, Intent data){
        authenticationDialog.onResult(resultCode, data);
        authenticationDialog.openMenu();
    }
}
