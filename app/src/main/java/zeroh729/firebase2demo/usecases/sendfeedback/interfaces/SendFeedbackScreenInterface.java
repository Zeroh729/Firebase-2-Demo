package zeroh729.firebase2demo.usecases.sendfeedback.interfaces;

public interface SendFeedbackScreenInterface {
    void showError(int errorCode);

    void showSendShoutoutSuccess(boolean b);

    void showSendQuestionSuccess(boolean b);

    void showSendRatingSuccess(boolean b);
}
