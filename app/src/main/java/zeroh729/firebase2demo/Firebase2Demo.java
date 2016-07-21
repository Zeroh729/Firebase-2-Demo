package zeroh729.firebase2demo;

import android.app.Application;
import android.content.Context;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

public class Firebase2Demo extends Application{
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);


        if (context == null)
            context = this.getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
