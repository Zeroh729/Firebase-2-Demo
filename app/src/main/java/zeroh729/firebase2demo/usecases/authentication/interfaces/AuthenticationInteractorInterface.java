package zeroh729.firebase2demo.usecases.authentication.interfaces;

import android.content.Intent;

import com.facebook.AccessToken;

import zeroh729.firebase2demo.interfaces.TaskCallback;

public interface AuthenticationInteractorInterface {
    void loginWithGoogleAccount(Intent data, TaskCallback callback);
    void handleFbAccessToken(AccessToken accessToken, TaskCallback callback);
}
