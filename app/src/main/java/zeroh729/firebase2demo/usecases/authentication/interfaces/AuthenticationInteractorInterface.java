package zeroh729.firebase2demo.usecases.authentication.interfaces;

import android.content.Intent;

import zeroh729.firebase2demo.interfaces.TaskCallback;

public interface AuthenticationInteractorInterface {
    void loginWithGoogleAccount(Intent data, TaskCallback callback);
}
