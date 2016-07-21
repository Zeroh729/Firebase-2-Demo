package zeroh729.firebase2demo.usecases.minoritycontest.interfaces;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.util.HashMap;

import zeroh729.firebase2demo.interfaces.FetchCallback;
import zeroh729.firebase2demo.interfaces.TaskCallback;
import zeroh729.firebase2demo.models.MinoryContest;

public interface ContestInteractorInterface {
    void subscribeToContestData(TaskCallback callback);
    MinoryContest getContestData();
    void saveAnswerLocally(String answer);
    void clearAnswerLocally();
    void uploadNoAnswer();
    boolean isGameRunning();
    void uploadAnswer(String answer);
    String readLocalAnswer();
}
