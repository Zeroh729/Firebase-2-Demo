package zeroh729.firebase2demo.usecases.minoritycontest;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TableRow;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.App;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Random;

import zeroh729.firebase2demo.Firebase2Demo;
import zeroh729.firebase2demo.R;
import zeroh729.firebase2demo.models.MinoryContest;
import zeroh729.firebase2demo.utils.MathUtil;

@EViewGroup(R.layout.content_contest_results)
public class ResultsScreen extends LinearLayout{
    private ContestPresenter presenter;
    private MinoryContest contest;

    @ViewById(R.id.pb_a)
    ProgressBar pb_1;

    @ViewById(R.id.pb_b)
    ProgressBar pb_2;

    @ViewById(R.id.pb_c)
    ProgressBar pb_3;

    @ViewById(R.id.pb_d)
    ProgressBar pb_4;

    @ViewById(R.id.tv_1)
    TextView tv_1;

    @ViewById(R.id.tv_2)
    TextView tv_2;

    @ViewById(R.id.tv_3)
    TextView tv_3;

    @ViewById(R.id.tv_4)
    TextView tv_4;

    @ViewById(R.id.tv_answer)
    TextView tv_answer;

    @ViewById(R.id.tv_cancelAnswer)
    TextView tv_cancelAnswer;

    @ViewById(R.id.tr_labels)
    TableRow tr_labels;

    HashMap<Integer, String> randomPosition;

    public ResultsScreen(Context context) {
        super(context);
        init();
    }

    public ResultsScreen(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ResultsScreen(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ResultsScreen(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public void init(){
        setOrientation(VERTICAL);
        randomPosition = new HashMap<>();
        randomPosition.put(getUniqRandomNumber(), "A");
        randomPosition.put(getUniqRandomNumber(), "B");
        randomPosition.put(getUniqRandomNumber(), "C");
        randomPosition.put(getUniqRandomNumber(), "D");
    }

    @AfterViews
    public void afterViews(){
        setLabelsVisible(false);
    }

    private int getUniqRandomNumber(){
        Random rand = new Random();
        int pos;
        do{
            pos = rand.nextInt(4);
        }while(randomPosition.containsKey(pos));
        return pos;
    }

    public void setPresenter(ContestPresenter presenter){
        this.presenter = presenter;
    }

    public void setAnswerCount(MinoryContest data){
        contest = presenter.getContestData();
        contest.setA(data.getA());
        contest.setB(data.getB());
        contest.setC(data.getC());
        contest.setD(data.getD());
        int total = contest.getA() + contest.getB() + contest.getC() + contest.getD();
        pb_1.setProgress(MathUtil.calculateParcentage(getCountOf(0), total));
        pb_2.setProgress(MathUtil.calculateParcentage(getCountOf(1), total));
        pb_3.setProgress(MathUtil.calculateParcentage(getCountOf(2), total));
        pb_4.setProgress(MathUtil.calculateParcentage(getCountOf(3), total));

        if(data.getUserAnswer().equals("")){
            tv_answer.setText("No Answer");
        }else {
            String message = "";
            switch(data.getUserAnswer()){
                case "A": message = data.getChoiceA(); break;
                case "B": message = data.getChoiceB(); break;
                case "C": message = data.getChoiceC(); break;
                case "D": message = data.getChoiceD(); break;
            }
            tv_answer.setText(data.getUserAnswer() + ". " + message);
        }
    }

    private int getCountOf(int pos){
        switch(randomPosition.get(pos)){
            case "A":
                return contest.getA();
            case "B":
                return contest.getB();
            case "C":
                return contest.getC();
            case "D":
                return contest.getD();
        }
        return 0;
    }

    @Click(R.id.tv_cancelAnswer)
    public void onClickCancel(){
        presenter.onCancelAnswer();
    }

    public void displayGameOver() {
        tv_cancelAnswer.setVisibility(View.GONE);
        setLabelsVisible(true);
    }

    public void displayGameRunning() {
        tv_cancelAnswer.setVisibility(View.VISIBLE);
        setLabelsVisible(false);
    }

    private void setLabelsVisible(boolean isVisible){
        if(tv_1 != null) {
            if (isVisible) {
                tv_1.setText(randomPosition.get(0) + "\n(" + pb_1.getProgress() + "%)");
                tv_2.setText(randomPosition.get(1) + "\n(" + pb_2.getProgress() + "%)");
                tv_3.setText(randomPosition.get(2) + "\n(" + pb_3.getProgress() + "%)");
                tv_4.setText(randomPosition.get(3) + "\n(" + pb_4.getProgress() + "%)");
            } else {
                tv_1.setText("");
                tv_2.setText("");
                tv_3.setText("");
                tv_4.setText("");
            }
        }
    }
}
