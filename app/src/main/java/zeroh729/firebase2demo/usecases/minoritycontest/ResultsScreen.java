package zeroh729.firebase2demo.usecases.minoritycontest;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;
import org.w3c.dom.Text;

import zeroh729.firebase2demo.R;
import zeroh729.firebase2demo.models.MinoryContest;
import zeroh729.firebase2demo.models.MinoryContest_;
import zeroh729.firebase2demo.utils.MathUtil;

@EViewGroup(R.layout.content_contest_results)
public class ResultsScreen extends LinearLayout{
    private ContestPresenter presenter;
    private MinoryContest contest;

    @ViewById(R.id.pb_a)
    ProgressBar pb_a;

    @ViewById(R.id.pb_b)
    ProgressBar pb_b;

    @ViewById(R.id.pb_c)
    ProgressBar pb_c;

    @ViewById(R.id.pb_d)
    ProgressBar pb_d;

    @ViewById(R.id.tv_cancelAnswer)
    TextView tv_cancelAnswer;

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
    }

    public void setPresenter(ContestPresenter presenter){
        this.presenter = presenter;
    }

    public void setAnswerCount(MinoryContest data){
        contest.setA(data.getA());
        contest.setB(data.getB());
        contest.setC(data.getC());
        contest.setD(data.getD());
        int total = contest.getA() + contest.getB() + contest.getC() + contest.getD();
        pb_a.setProgress(MathUtil.calculateParcentage(contest.getA(), total));
        pb_b.setProgress(MathUtil.calculateParcentage(contest.getB(), total));
        pb_c.setProgress(MathUtil.calculateParcentage(contest.getC(), total));
        pb_d.setProgress(MathUtil.calculateParcentage(contest.getD(), total));
    }

    @Click(R.id.tv_cancelAnswer)
    public void onClickCancel(){
        presenter.onCancelAnswer();
    }

    public void displayGameOver() {
        tv_cancelAnswer.setVisibility(View.GONE);
    }

    public void displayGameRunning() {
        tv_cancelAnswer.setVisibility(View.VISIBLE);
    }
}
