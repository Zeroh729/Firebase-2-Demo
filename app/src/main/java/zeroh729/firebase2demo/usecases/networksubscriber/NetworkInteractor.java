package zeroh729.firebase2demo.usecases.networksubscriber;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import zeroh729.firebase2demo.usecases.networksubscriber.interfaces.NetworkInteractorInterface;
import zeroh729.firebase2demo.utils.NetworkUtil;

public class NetworkInteractor extends BroadcastReceiver implements NetworkInteractorInterface {
    private static Callback callback;

    @Override
    public void subscribe(Callback callback) {
        this.callback = callback;
    }

    @Override
    public void unsubscribe() {
        this.callback = null;
    }

    @Override
    public boolean isConnected() {
        return NetworkUtil.getConnectionStatus() == NetworkUtil.STATUS_CONNECTED;
    }

    @Override
    public boolean isConnecting() {
        return NetworkUtil.getConnectionStatus() == NetworkUtil.STATUS_CONNECTING;
    }

    @Override
    public boolean isDisconnected() {
        return NetworkUtil.getConnectionStatus() == NetworkUtil.STATUS_DISCONNECTED;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if(callback != null)
            switch(NetworkUtil.getConnectionStatus()){
                case NetworkUtil.STATUS_CONNECTED:
                    callback.onConnected();
                    break;
                case NetworkUtil.STATUS_CONNECTING:
                    callback.onConnecting();
                    break;
                default:
                    callback.onDisconnect();
                    break;
            }
    }
}
