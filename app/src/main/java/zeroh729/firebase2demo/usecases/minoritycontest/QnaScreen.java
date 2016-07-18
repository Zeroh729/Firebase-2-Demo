package zeroh729.firebase2demo.usecases.minoritycontest;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import zeroh729.firebase2demo.R;
import zeroh729.firebase2demo.usecases.minoritycontest.interfaces.ContestScreenInterface;

@EViewGroup(R.layout.content_contest_qna)
public class QnaScreen extends LinearLayout{
    private ContestPresenter presenter;

    @ViewById(R.id.tv_question)
    TextView tv_question;

    public QnaScreen(Context context) {
        super(context);
        init();
    }

    public QnaScreen(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public QnaScreen(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public QnaScreen(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public void init(){
        setOrientation(VERTICAL);
    }

    public void setQuestion(String question){
        tv_question.setText(question);
    }

    public void setPresenter(ContestPresenter presenter){
        this.presenter = presenter;
    }

    @Click(R.id.btn_a)
    public void onClickA(){
        presenter.onAnswer("A");
    }

    @Click(R.id.btn_b)
    public void onClickB(){
        presenter.onAnswer("B");
    }

    @Click(R.id.btn_c)
    public void onClickC(){
        presenter.onAnswer("C");
    }

    @Click(R.id.btn_d)
    public void onClickD(){
        presenter.onAnswer("D");
    }
}
