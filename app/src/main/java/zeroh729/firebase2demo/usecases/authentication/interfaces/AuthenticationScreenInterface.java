package zeroh729.firebase2demo.usecases.authentication.interfaces;

public interface AuthenticationScreenInterface {
    void showGoogleLogin();
    void showError(int errorCode);
    void displayWaitForAuthChangeListener();
    void hideFbLoginLoading();
}
