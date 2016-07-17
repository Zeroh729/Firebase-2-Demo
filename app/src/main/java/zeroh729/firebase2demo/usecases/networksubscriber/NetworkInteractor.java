package zeroh729.firebase2demo.usecases.networksubscriber;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import zeroh729.firebase2demo.usecases.networksubscriber.interfaces.NetworkInteractorInterface;
import zeroh729.firebase2demo.utils.NetworkUtil;

public class NetworkInteractor extends BroadcastReceiver implements NetworkInteractorInterface {
    private Callback callback;
    private int status = NetworkUtil.STATUS_CONNECTING;

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
        return status == NetworkUtil.STATUS_CONNECTED ? true : false;
    }

    @Override
    public boolean isConnecting() {
        return status == NetworkUtil.STATUS_CONNECTING? true : false;
    }

    @Override
    public boolean isDisconnected() {
        return status == NetworkUtil.STATUS_DISCONNECTED? true : false;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        switch(NetworkUtil.getConnectionStatus(context)){
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
