package zeroh729.firebase2demo.usecases.accountsubscriber.interfaces;

import zeroh729.firebase2demo.models.User;

public interface AccountSubScreenInterface {
    void showLoginScreen();
    void showHomeScreen();
    void showWelcome(User user);
}
