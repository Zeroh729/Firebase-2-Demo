package zeroh729.firebase2demo.usecases.sendfeedback;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.RatingBar;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.EFragment;

import zeroh729.firebase2demo.R;
import zeroh729.firebase2demo.models.Question;
import zeroh729.firebase2demo.models.Shoutout;
import zeroh729.firebase2demo.models.User;
import zeroh729.firebase2demo.usecases.sendfeedback.interfaces.SendFeedbackScreenInterface;

@EBean
public class SendFeedbackScreen implements SendFeedbackScreenInterface{
    private SendFeedbackPresenter presenter;
    private QuestionDialog questionDialog;
    private RatingDialog ratingDialog;
    private ShoutoutDialog shoutoutDialog;

    public SendFeedbackScreen(){
        presenter = new SendFeedbackPresenter(this);
    }

    public void showQuestionDialog(Context context, User user){
        questionDialog = new QuestionDialog(context, presenter);
        questionDialog.setContactInfo(user.getContactInfo());
        questionDialog.show();
    }

    public void showRatingDialog(Context context){
        ratingDialog = new RatingDialog(context, presenter);
        ratingDialog.show();
    }

    public void showShoutoutDialog(Context context){
        shoutoutDialog = new ShoutoutDialog(context, presenter);
        shoutoutDialog.show();
    }

    @Override
    public void showError(int errorCode) {

    }

    @Override
    public void showSendShoutoutSuccess(boolean b) {

    }

    @Override
    public void showSendQuestionSuccess(boolean b) {

    }

    @Override
    public void showSendRatingSuccess(boolean b) {

    }
}
