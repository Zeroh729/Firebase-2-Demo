package zeroh729.firebase2demo.usecases.sendfeedback;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.RatingBar;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.EFragment;

import zeroh729.firebase2demo.R;
import zeroh729.firebase2demo.usecases.sendfeedback.interfaces.SendFeedbackScreenInterface;

@EBean
public class SendFeedbackDialog extends Dialog implements SendFeedbackScreenInterface{
    SendFeedbackPresenter presenter;

    public SendFeedbackDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_sendquestion);

        presenter = new SendFeedbackPresenter(this);
    }

    @Override
    public void showError(int errorCode) {

    }

    @Override
    public void showSendShoutoutSuccess(boolean b) {
        //Snackbar
    }

    @Override
    public void showSendQuestionSuccess(boolean b) {

    }

    @Override
    public void showSendRatingSuccess(boolean b) {

    }
}
