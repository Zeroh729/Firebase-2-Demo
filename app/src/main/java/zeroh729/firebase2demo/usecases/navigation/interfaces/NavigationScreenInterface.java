package zeroh729.firebase2demo.usecases.navigation.interfaces;

import zeroh729.firebase2demo.models.User;

public interface NavigationScreenInterface {
    void displayQuestionScreen(User user);
    void displayMenuList();
    void displayRatingScreen();
    void displayShoutoutScreen();
    void displayLogin();
}
