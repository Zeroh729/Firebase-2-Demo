package zeroh729.firebase2demo.usecases.authentication;

import android.content.Intent;
import android.support.v4.app.Fragment;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OnActivityResult;

import zeroh729.firebase2demo.usecases.authentication.interfaces.AuthenticationScreenInterface;

@EFragment
public class AuthenticationFragment extends Fragment implements AuthenticationScreenInterface {
    private final int RCODE_GOOGLE_SIGNIN = 9001;
    private AuthenticationPresenter presenter;

    @AfterViews()
    public void afterViews(){
        presenter = new AuthenticationPresenter(this);
    }

    @OnActivityResult(RCODE_GOOGLE_SIGNIN)
    public void onResult(int resultCode, Intent data){
        presenter.onSelectGoogleAccount(data);
    }

    @Override
    public void showGoogleLogin() {

    }

    @Override
    public void openMenu() {

    }

    @Override
    public void showError(int errorCode) {

    }
}
