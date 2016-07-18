package zeroh729.firebase2demo.usecases.minoritycontest;

import java.util.HashMap;

import zeroh729.firebase2demo.interfaces.FetchCallback;
import zeroh729.firebase2demo.usecases.FirebaseInteractor;
import zeroh729.firebase2demo.usecases.minoritycontest.interfaces.ContestInteractorInterface;
import zeroh729.firebase2demo.usecases.minoritycontest.interfaces.ContestScreenInterface;

public class ContestPresenter {
    private ContestScreenInterface screen;
    private ContestInteractorInterface system;

    public ContestPresenter(ContestScreenInterface screen){
        this.screen = screen;
        system = new FirebaseInteractor.ContestInteractor();
    }

    public void onAnswer(String answer){
        system.saveAnswerLocally(answer);
        system.uploadAnswer(answer);
        screen.displayGraphScreen();
    }

    public void onCancelAnswer(){
        system.clearAnswerLocally();
        system.uploadNoAnswer();
        screen.displayQnaScreen();
    }

    public void subscribeToContest(){
        system.subscribeToContestData(new FetchCallback() {
            @Override
            public void onSuccess(HashMap data) {
                if(system.isGameRunning(data)) {
                    screen.updateContestViews(system.getContestData(data));
                }else{
                    screen.displayGameOver();
                }
            }

            @Override
            public void onFail(int errorCode) {
                screen.showError(errorCode);
            }
        });
    }
}
