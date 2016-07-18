package zeroh729.firebase2demo.usecases.authentication;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OnActivityResult;

import zeroh729.firebase2demo.R;
import zeroh729.firebase2demo.usecases.authentication.interfaces.AuthenticationScreenInterface;

@EBean
public class AuthenticationFragment extends Dialog implements AuthenticationScreenInterface {
    private final int RCODE_GOOGLE_SIGNIN = 9001;
    private AuthenticationPresenter presenter;

    public AuthenticationFragment(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_login);
        presenter = new AuthenticationPresenter(this);
    }

    @Click(R.id.btn_googlelogin)
    public void onClick(){
        presenter.onClickGoogleLogin();
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
