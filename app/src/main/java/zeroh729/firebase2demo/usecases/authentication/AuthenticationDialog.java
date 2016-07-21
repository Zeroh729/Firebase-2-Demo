package zeroh729.firebase2demo.usecases.authentication;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.androidannotations.annotations.EBean;

import zeroh729.firebase2demo.R;
import zeroh729.firebase2demo.usecases.authentication.interfaces.AuthenticationScreenInterface;

@EBean
public class AuthenticationDialog extends Dialog implements AuthenticationScreenInterface {
    public final static int RCODE_GOOGLE_SIGNIN = 9001;
    private AuthenticationPresenter presenter;
    private CallbackManager callbackManager;
    private LoginButton btn_fblogin;

    public AuthenticationDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_login);
        presenter = new AuthenticationPresenter(this);
        callbackManager = CallbackManager.Factory.create();
        btn_fblogin = (LoginButton)findViewById(R.id.btn_fblogin);
        btn_fblogin.setReadPermissions("email", "public_profile");
        btn_fblogin.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                presenter.onSuccessFbLogin(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                presenter.onCancelFbLogin();
            }

            @Override
            public void onError(FacebookException error) {
                presenter.onFailFbLogin();
            }
        });
    }

    public void onResultGoogle(int resultCode, Intent data){
        presenter.onSelectGoogleAccount(data);
    }

    @Override
    public void showGoogleLogin() {

    }

    @Override
    public void showError(int errorCode) {

    }

    @Override
    public void displayWaitForAuthChangeListener() {
        dismiss();
    }

    @Override
    public void hideFbLoginLoading() {

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
