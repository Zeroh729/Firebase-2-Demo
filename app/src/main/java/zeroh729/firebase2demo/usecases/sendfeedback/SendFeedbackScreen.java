package zeroh729.firebase2demo.usecases.sendfeedback;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.RatingBar;
import android.widget.Toast;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.EFragment;

import zeroh729.firebase2demo.Firebase2Demo;
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
    private View viewForSnack;

    public SendFeedbackScreen(){
        presenter = new SendFeedbackPresenter(this);
    }

    public void showQuestionDialog(Context context, User user){
        questionDialog = new QuestionDialog(context, presenter);
        questionDialog.setUser(user);
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
        Toast.makeText(Firebase2Demo.getContext(), "Something went wrong lol #suchislife", Toast.LENGTH_SHORT);
    }

    @Override
    public void showSendShoutoutSuccess(boolean b) {
        String message = b ? "Shoutout sent!" : "Not connected. Will send once online!";
        Snackbar.make(viewForSnack, message, Snackbar.LENGTH_SHORT).show();
        shoutoutDialog.dismiss();
    }

    @Override
    public void showSendQuestionSuccess(boolean b) {
        String message = b ? "Question sent! Will (with 99% assurance) get back to you within the week!" : "Not connected. Will send once online!";
        Snackbar.make(viewForSnack, message, Snackbar.LENGTH_LONG).show();
        questionDialog.dismiss();
    }

    @Override
    public void showSendRatingSuccess(boolean b) {
        ratingDialog.dismiss();
    }

    public void setViewForSnack(View viewForSnack) {
        this.viewForSnack = viewForSnack;
    }
}
