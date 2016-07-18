package zeroh729.firebase2demo.usecases.minoritycontest.interfaces;

import java.util.HashMap;

import zeroh729.firebase2demo.interfaces.FetchCallback;
import zeroh729.firebase2demo.models.MinoryContest;


public interface ContestInteractorInterface {
    void subscribeToContestData(FetchCallback callback);
    MinoryContest getContestData(HashMap data);
    void saveAnswerLocally(String answer);
    void clearAnswerLocally();
    void uploadNoAnswer();
    boolean isGameRunning(HashMap data);
    void uploadAnswer(String answer);
}
