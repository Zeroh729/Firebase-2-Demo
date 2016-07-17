package zeroh729.firebase2demo.usecases.networksubscriber.interfaces;

public interface NetworkInteractorInterface {
    void subscribe(Callback callback);
    void unsubscribe();
    boolean isConnected();
    boolean isConnecting();
    boolean isDisconnected();
    interface Callback{
        void onConnected();
        void onConnecting();
        void onDisconnect();
    }
}
