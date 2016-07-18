package zeroh729.firebase2demo.usecases.contentsubscriber.interfaces;

import java.util.ArrayList;
import java.util.HashMap;

import zeroh729.firebase2demo.interfaces.FetchCallback;
import zeroh729.firebase2demo.models.Shoutout;

public interface ContentInteractorInterface {
    void subscribeToCurrentSlideNumber(FetchCallback callback);

    int getSlideNumber(HashMap data);

    void subscribeToShoutouts(FetchCallback fetchCallback);

    Shoutout getShoutout(HashMap data);
}
