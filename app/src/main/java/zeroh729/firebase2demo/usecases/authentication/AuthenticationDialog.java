package zeroh729.firebase2demo.usecases.authentication;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.androidannotations.annotations.EBean;

import zeroh729.firebase2demo.R;
import zeroh729.firebase2demo.usecases.authentication.interfaces.AuthenticationScreenInterface;

@EBean
public class AuthenticationDialog extends Dialog implements AuthenticationScreenInterface, View.OnClickListener {
    public final static int RCODE_GOOGLE_SIGNIN = 9001;
    private AuthenticationPresenter presenter;
    private Button btn_googleLogin;

    public AuthenticationDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_login);
        presenter = new AuthenticationPresenter(this);
        btn_googleLogin = (Button)findViewById(R.id.btn_googlelogin);
        btn_googleLogin.setOnClickListener(this);
    }

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

    public void onClick(View view) {
        if(view.getId() == R.id.btn_googlelogin){
            presenter.onClickGoogleLogin();
        }
    }
}
