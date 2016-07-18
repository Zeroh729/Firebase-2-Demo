package zeroh729.firebase2demo.usecases.contentsubscriber;

import java.util.HashMap;

import zeroh729.firebase2demo.interfaces.FetchCallback;
import zeroh729.firebase2demo.usecases.FirebaseInteractor;
import zeroh729.firebase2demo.usecases.contentsubscriber.interfaces.ContentInteractorInterface;
import zeroh729.firebase2demo.usecases.contentsubscriber.interfaces.ContentSubScreenInterface;

public class ContentPresenter {
    private ContentSubScreenInterface screen;
    private ContentInteractorInterface system;

    public ContentPresenter(ContentSubScreenInterface screen) {
        this.screen = screen;
        system = new FirebaseInteractor.ContentInteractor();
    }

    public void subscribeToCurrentSlideNumber(){
        system.subscribeToCurrentSlideNumber(new FetchCallback(){
            @Override
            public void onSuccess(HashMap data) {
                screen.displaySlideNumber(system.getSlideNumber(data));
            }

            @Override
            public void onFail(int errorCode) {
                screen.showError(errorCode);
            }
        });
    }

    public void subscribeToShoutouts(){
        system.subscribeToShoutouts(new FetchCallback(){
            @Override
            public void onSuccess(HashMap data) {
                if(screen.isStatusbarIsEmpty()) {
                    screen.showNewShoutout(system.getShoutout(data));
                }
            }

            @Override
            public void onFail(int errorCode) {
                screen.showError(errorCode);
            }
        });
    }
}
