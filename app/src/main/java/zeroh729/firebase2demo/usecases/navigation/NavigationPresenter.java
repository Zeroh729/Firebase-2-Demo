package zeroh729.firebase2demo.usecases.navigation;

import zeroh729.firebase2demo.models.User;
import zeroh729.firebase2demo.usecases.FirebaseInteractor;
import zeroh729.firebase2demo.usecases.navigation.interfaces.NavigationInteractorInterface;
import zeroh729.firebase2demo.usecases.navigation.interfaces.NavigationScreenInterface;

public class NavigationPresenter {
    private NavigationScreenInterface screen;
    private NavigationInteractorInterface system;

    public NavigationPresenter(NavigationScreenInterface screen){
        this.screen = screen;
        system = new FirebaseInteractor.NavigationInteractor();
    }

    public void onClickMenu(){
        if(system.getUser().getId() == null)
            screen.displayLogin();
        else
            screen.displayMenuList();
    }

    public void onClickQuestion(){
        User user = system.getUser();
        screen.displayQuestionScreen(user);
    }

    public void onClickRating(){
        screen.displayRatingScreen();
    }

    public void onClickShoutout(){
        screen.displayShoutoutScreen();
    }

}
