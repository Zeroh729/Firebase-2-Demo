package zeroh729.firebase2demo.usecases;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

import java.util.ArrayList;

import zeroh729.firebase2demo.R;
import zeroh729.firebase2demo.models.Shoutout;
import zeroh729.firebase2demo.models.User;
import zeroh729.firebase2demo.usecases.contentsubscriber.ContentPresenter;
import zeroh729.firebase2demo.usecases.contentsubscriber.interfaces.ContentSubScreenInterface;
import zeroh729.firebase2demo.usecases.navigation.NavigationPresenter;
import zeroh729.firebase2demo.usecases.navigation.interfaces.NavigationScreenInterface;
import zeroh729.firebase2demo.usecases.networksubscriber.NetworkPresenter;
import zeroh729.firebase2demo.usecases.networksubscriber.interfaces.NetworkSubScreenInterface;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements NetworkSubScreenInterface, ContentSubScreenInterface, NavigationScreenInterface{
    private NetworkPresenter networkPresenter;
    private ContentPresenter contentPresenter;
    private NavigationPresenter navigationPresenter;

    @AfterViews
    public void afterViews(){
        networkPresenter = new NetworkPresenter(this);
        contentPresenter = new ContentPresenter(this);
        navigationPresenter = new NavigationPresenter(this);

        networkPresenter.subscribe();
        contentPresenter.subscribeToCurrentSlideNumber();
        contentPresenter.subscribeToShoutouts();
    }

    @Click(R.id.fab_menu)
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
    public void displaySlideNumber(int slideNumber) {

    }

    @Override
    public void showError(int errorCode) {

    }

    @Override
    public void showNewShoutout(Shoutout shoutOuts) {

    }

    @Override
    public boolean isStatusbarIsEmpty() {
        return false;
    }

    @Override
    public void displayConnectedStatus() {

    }

    @Override
    public void displayConnectingStatus() {

    }

    @Override
    public void displayDisconnectedStatus() {

    }

    @Override
    public void displayQuestionScreen(User user) {

    }

    @Override
    public void displayMenuList() {

    }

    @Override
    public void displayRatingScreen() {

    }

    @Override
    public void displayShoutoutScreen() {

    }

    @Override
    public void displayLogin() {

    }
}
