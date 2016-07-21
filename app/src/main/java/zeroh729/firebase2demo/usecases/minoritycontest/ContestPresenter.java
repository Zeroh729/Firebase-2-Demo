package zeroh729.firebase2demo.usecases.minoritycontest;

import org.androidannotations.annotations.Bean;

import java.util.HashMap;

import zeroh729.firebase2demo.interfaces.FetchCallback;
import zeroh729.firebase2demo.interfaces.TaskCallback;
import zeroh729.firebase2demo.models.MinoryContest;
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

    public void onLoadApp(){
        if(system.isGameRunning() && system.readLocalAnswer().equals("")){
            screen.displayQnaScreen();
        }else{
            screen.displayGraphScreen();
        }
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
        system.subscribeToContestData(new TaskCallback() {
            @Override
            public void onSuccess() {
                screen.updateContestViews(system.getContestData());
            }

            @Override
            public void onFail(int errorCode) {
                screen.showError(errorCode);
            }
        });
    }

    public MinoryContest getContestData() {
        return system.getContestData();
    }
}
