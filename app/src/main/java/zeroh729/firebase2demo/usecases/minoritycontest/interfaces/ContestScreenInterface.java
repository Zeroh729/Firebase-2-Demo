package zeroh729.firebase2demo.usecases.minoritycontest.interfaces;

import zeroh729.firebase2demo.models.MinoryContest;

public interface ContestScreenInterface {
    void displayGraphScreen();
    void displayQnaScreen();
    void showError(int errorCode);
    void updateContestViews(MinoryContest contestData);
    void displayGameOver();
}
