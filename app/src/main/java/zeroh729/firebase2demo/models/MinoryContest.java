package zeroh729.firebase2demo.models;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.HashMap;

@EBean(scope = EBean.Scope.Singleton)
public class MinoryContest {
    private boolean isGameRunning = true;
    private HashMap<String, String> responses;
    private int a = 0;
    private int b = 0;
    private int c = 0;
    private int d = 0;
    private String choiceA = "";
    private String choiceB = "";
    private String choiceC = "";
    private String choiceD = "";
    private String question = "";
    private String userAnswer = "";

    public HashMap<String, String> getResponses() {
        if(responses == null)
            responses = new HashMap<>();
        return responses;
    }

    public void setResponses(HashMap<String, String> responses) {
        this.responses = responses;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    public String getChoiceA() {
        return choiceA;
    }

    public void setChoiceA(String choiceA) {
        this.choiceA = choiceA;
    }

    public String getChoiceB() {
        return choiceB;
    }

    public void setChoiceB(String choiceB) {
        this.choiceB = choiceB;
    }

    public String getChoiceC() {
        return choiceC;
    }

    public void setChoiceC(String choiceC) {
        this.choiceC = choiceC;
    }

    public String getChoiceD() {
        return choiceD;
    }

    public void setChoiceD(String choiceD) {
        this.choiceD = choiceD;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
        if(a < 0)
            this.a = 0;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
        if(b < 0)
            this.b = 0;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
        if(c < 0)
            this.c = 0;
    }

    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
        if(d < 0)
            this.d = 0;
    }

    public boolean isGameRunning() {
        return isGameRunning;
    }

    public void setGameRunning(boolean gameRunning) {
        isGameRunning = gameRunning;
    }
}
