package zeroh729.firebase2demo.usecases.accountsubscriber.interfaces;

import zeroh729.firebase2demo.models.User;

public interface AccountSubInteractorInterface {
    void subscribeToAccountState(AuthStateCallback onAuthStateChanged);

    void unsubscribe();

    User getUser();

    interface AuthStateCallback {
        void onUserIsLoggedIn();
        void onUserIsLoggedOut();
    }
}
