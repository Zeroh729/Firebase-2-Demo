package zeroh729.firebase2demo.usecases.sendfeedback;

import android.support.v4.app.Fragment;

import org.androidannotations.annotations.EFragment;

import zeroh729.firebase2demo.usecases.sendfeedback.interfaces.SendFeedbackScreenInterface;

@EFragment
public class SendFeedbackFragment extends Fragment implements SendFeedbackScreenInterface{

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
