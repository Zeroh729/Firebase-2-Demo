package zeroh729.firebase2demo.usecases.contentsubscriber.interfaces;

import java.util.ArrayList;

import zeroh729.firebase2demo.models.Shoutout;

public interface ContentSubScreenInterface {
    void displaySlideNumber(int slideNumber);
    void showError(int errorCode);

    void showNewShoutout(Shoutout shoutOuts);

    boolean isStatusbarIsEmpty();
}
