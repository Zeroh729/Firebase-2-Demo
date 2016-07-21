package zeroh729.firebase2demo.usecases.authentication;

import android.content.Intent;

import com.facebook.AccessToken;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;

import zeroh729.firebase2demo.interfaces.TaskCallback;
import zeroh729.firebase2demo.usecases.FirebaseInteractor;
import zeroh729.firebase2demo.usecases.authentication.interfaces.AuthenticationInteractorInterface;
import zeroh729.firebase2demo.usecases.authentication.interfaces.AuthenticationScreenInterface;
import zeroh729.firebase2demo.values.ErrorCodeConstants;

public class AuthenticationPresenter {
    private AuthenticationScreenInterface screen;
    public AuthenticationInteractorInterface system;

    public AuthenticationPresenter(AuthenticationScreenInterface screenInterface){
        this.screen = screenInterface;
        system = new FirebaseInteractor.AuthenticationInteractor();
    }

    public void onSignInAnonymous(){
        
    }

    public void onClickGoogleLogin(){
        screen.showGoogleLogin();
    }

    public void onSuccessFbLogin(AccessToken accessToken){
        system.handleFbAccessToken(accessToken, new TaskCallback() {
            @Override
            public void onSuccess() {
                screen.displayWaitForAuthChangeListener();
            }

            @Override
            public void onFail(int errorCode) {
                screen.showError(errorCode);
            }
        });
    }

    public void onSelectGoogleAccount(Intent data) {
        system.loginWithGoogleAccount(data, new TaskCallback(){
            @Override
            public void onSuccess() {
                screen.displayWaitForAuthChangeListener();
            }

            @Override
            public void onFail(int errorCode) {
                screen.showError(errorCode);
            }
        });
    }

    public void onFailFbLogin() {
        screen.hideFbLoginLoading();
        screen.showError(ErrorCodeConstants.FB_LOGIN_ERROR);
    }

    public void onCancelFbLogin() {
        screen.hideFbLoginLoading();
    }

}
