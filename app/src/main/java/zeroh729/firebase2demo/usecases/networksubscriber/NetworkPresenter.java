package zeroh729.firebase2demo.usecases.networksubscriber;

import zeroh729.firebase2demo.usecases.networksubscriber.interfaces.NetworkInteractorInterface;
import zeroh729.firebase2demo.usecases.networksubscriber.interfaces.NetworkSubScreenInterface;

public class NetworkPresenter {
    private NetworkSubScreenInterface screen;
    private NetworkInteractorInterface system;

    public NetworkPresenter(NetworkSubScreenInterface screen){
        this.screen = screen;
        system = new NetworkInteractor();
    }

    public void subscribeToNetworkState(){
        system.subscribe(new NetworkInteractorInterface.Callback() {
            @Override
            public void onConnected() {
                screen.displayConnectedStatus();
            }

            @Override
            public void onConnecting() {
                screen.displayConnectingStatus();
            }

            @Override
            public void onDisconnect() {
                screen.displayDisconnectedStatus();
            }
        });
    }

    public void unsubscribe(){
        system.unsubscribe();
    }
}
