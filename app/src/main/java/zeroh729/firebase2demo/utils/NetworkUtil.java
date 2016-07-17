package zeroh729.firebase2demo.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkUtil {
    public final static int TYPE_NOT_CONNECTED = 0;
    public final static int TYPE_WIFI = 1;
    public final static int TYPE_MOBILE = 2;

    public final static int STATUS_CONNECTED = 0;
    public final static int STATUS_CONNECTING = 1;
    public final static int STATUS_DISCONNECTED = 2;

    public static int getConnectionType(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (null != activeNetwork) {
            if(activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                return TYPE_WIFI;

            if(activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                return TYPE_MOBILE;
        }
        return TYPE_NOT_CONNECTED;
    }

    public static int getConnectionStatus(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (null != activeNetwork) {
            if(activeNetwork.isConnected())
                return STATUS_CONNECTED;

            if(activeNetwork.isConnectedOrConnecting())
                return STATUS_CONNECTING;
        }
        return STATUS_DISCONNECTED;
    }

}