package zeroh729.firebase2demo.usecases.authentication;

import android.content.Intent;

import zeroh729.firebase2demo.interfaces.TaskCallback;
import zeroh729.firebase2demo.usecases.FirebaseInteractor;
import zeroh729.firebase2demo.usecases.authentication.interfaces.AuthenticationInteractorInterface;
import zeroh729.firebase2demo.usecases.authentication.interfaces.AuthenticationScreenInterface;

public class AuthenticationPresenter {
    private AuthenticationScreenInterface screen;
    public AuthenticationInteractorInterface system;

    public AuthenticationPresenter(AuthenticationScreenInterface screenInterface){
        this.screen = screenInterface;
        system = new FirebaseInteractor.AuthenticationInteractor();
    }

    public void onClickGoogleLogin(){
        screen.showGoogleLogin();
    }

    public void onSelectGoogleAccount(Intent data) {
        system.loginWithGoogleAccount(data, new TaskCallback(){
            @Override
            public void onSuccess() {
                screen.openMenu();
            }

            @Override
            public void onFail(int errorCode) {
                screen.showError(errorCode);
            }
        });
    }
}
