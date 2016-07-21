package zeroh729.firebase2demo.usecases.contentsubscriber.interfaces;

import java.util.ArrayList;
import java.util.HashMap;

import zeroh729.firebase2demo.interfaces.DoneCallback;
import zeroh729.firebase2demo.interfaces.FetchCallback;
import zeroh729.firebase2demo.models.Shoutout;

public interface ContentInteractorInterface {
    void subscribeToShoutouts(FetchCallback fetchCallback);
    Shoutout getShoutout(HashMap<String, String> data);
}
