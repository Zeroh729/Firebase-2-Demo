package zeroh729.firebase2demo.usecases;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.androidannotations.annotations.EActivity;

import java.util.ArrayList;

import zeroh729.firebase2demo.R;
import zeroh729.firebase2demo.models.Shoutout;
import zeroh729.firebase2demo.usecases.contentsubscriber.ContentPresenter;
import zeroh729.firebase2demo.usecases.contentsubscriber.interfaces.ContentSubScreenInterface;
import zeroh729.firebase2demo.usecases.networksubscriber.NetworkPresenter;
import zeroh729.firebase2demo.usecases.networksubscriber.interfaces.NetworkSubScreenInterface;

@EActivity
public class MainActivity extends AppCompatActivity implements NetworkSubScreenInterface, ContentSubScreenInterface {
    private NetworkPresenter networkPresenter;
    private ContentPresenter contentPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        networkPresenter = new NetworkPresenter(this);
        contentPresenter = new ContentPresenter(this);

        networkPresenter.subscribe();
        contentPresenter.subscribeToCurrentSlideNumber();
        contentPresenter.subscribeToShoutouts();
    }

    @Override
    public void displaySlideNumber(int slideNumber) {

    }

    @Override
    public void showError(int errorCode) {

    }

    @Override
    public void showShoutout(ArrayList<Shoutout> shoutOuts) {

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
}
