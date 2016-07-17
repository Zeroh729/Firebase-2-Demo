package zeroh729.firebase2demo.usecases.authentication.interfaces;

public interface AuthenticationScreenInterface {
    void showGoogleLogin();
    void openMenu();
    void showError(int errorCode);
}
