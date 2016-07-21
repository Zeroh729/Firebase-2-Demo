package zeroh729.firebase2demo.services;

import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.messaging.FirebaseMessaging;

public class TokenService extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {
        FirebaseMessaging.getInstance().subscribeToTopic("GDG");
    }
}
