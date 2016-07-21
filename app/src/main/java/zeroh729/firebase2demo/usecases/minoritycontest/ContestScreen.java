package zeroh729.firebase2demo.usecases.minoritycontest;

import android.view.View;

import org.androidannotations.annotations.EBean;

import zeroh729.firebase2demo.models.MinoryContest;
import zeroh729.firebase2demo.usecases.minoritycontest.interfaces.ContestScreenInterface;


@EBean
public class ContestScreen implements ContestScreenInterface {
    private QnaScreen qnaScreen;
    private ResultsScreen resultsScreen;

    public void setQnaScreen(ContestPresenter presenter, QnaScreen qnaScreen) {
        this.qnaScreen = qnaScreen;
        qnaScreen.setPresenter(presenter);
    }

    public void setResultsScreen(ContestPresenter presenter, ResultsScreen resultsScreen) {
        this.resultsScreen = resultsScreen;
        resultsScreen.setPresenter(presenter);
    }

    @Override
    public void displayGraphScreen() {
        resultsScreen.setVisibility(View.VISIBLE);
        qnaScreen.setVisibility(View.INVISIBLE);
    }

    @Override
    public void displayQnaScreen() {
        qnaScreen.setVisibility(View.VISIBLE);
        resultsScreen.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showError(int errorCode) {

    }

    @Override
    public void updateContestViews(MinoryContest contestData) {
        resultsScreen.setAnswerCount(contestData);
        qnaScreen.setQuestionAndAnswer(contestData);
        if(contestData.isGameRunning()) {
            resultsScreen.displayGameRunning();
            if(contestData.getUserAnswer().equals("")){
                displayQnaScreen();
            }else{
                displayGraphScreen();
            }
        }
        else {
            displayGameOver();
        }
    }

    @Override
    public void displayGameOver() {
        qnaScreen.setVisibility(View.INVISIBLE);
        resultsScreen.setVisibility(View.VISIBLE);
        resultsScreen.displayGameOver();
    }
}
