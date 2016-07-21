package zeroh729.firebase2demo.usecases.accountsubscriber;

import zeroh729.firebase2demo.usecases.FirebaseInteractor;
import zeroh729.firebase2demo.usecases.accountsubscriber.interfaces.AccountSubInteractorInterface;
import zeroh729.firebase2demo.usecases.accountsubscriber.interfaces.AccountSubScreenInterface;

public class AccountSubscriberPresenter {
    private AccountSubScreenInterface screen;
    private AccountSubInteractorInterface system;

    public AccountSubscriberPresenter(AccountSubScreenInterface screen){
        this.screen = screen;
        this.system = new FirebaseInteractor.AccountStateInteractor();
    }

    public void subscribeToAccountState(){
        system.subscribeToAccountState(new AccountSubInteractorInterface.AuthStateCallback(){
            @Override
            public void onUserIsLoggedIn() {
                screen.showHomeScreen();
                screen.showWelcome(system.getUser());
            }

            @Override
            public void onUserIsLoggedOut() {
                screen.showLoginScreen();
            }
        });
    }

    public void unsubscribe(){
        system.unsubscribe();
    }
}
