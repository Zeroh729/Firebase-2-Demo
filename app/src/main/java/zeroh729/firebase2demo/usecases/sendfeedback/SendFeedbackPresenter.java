package zeroh729.firebase2demo.usecases.sendfeedback;

import zeroh729.firebase2demo.interfaces.TaskCallback;
import zeroh729.firebase2demo.models.Question;
import zeroh729.firebase2demo.models.Shoutout;
import zeroh729.firebase2demo.models.SlideRating;
import zeroh729.firebase2demo.usecases.FirebaseInteractor;
import zeroh729.firebase2demo.usecases.networksubscriber.NetworkInteractor;
import zeroh729.firebase2demo.usecases.networksubscriber.interfaces.NetworkInteractorInterface;
import zeroh729.firebase2demo.usecases.sendfeedback.interfaces.SendFeedbackInteractorInterface;
import zeroh729.firebase2demo.usecases.sendfeedback.interfaces.SendFeedbackScreenInterface;
import zeroh729.firebase2demo.utils.DateUtil;

public class SendFeedbackPresenter {
    private SendFeedbackScreenInterface screen;
    private SendFeedbackInteractorInterface system;
    private NetworkInteractorInterface networkSystem;

    public SendFeedbackPresenter(SendFeedbackScreenInterface screen){
        this.screen = screen;
        system = new FirebaseInteractor.SendFeedbackInteractor();
        networkSystem = new NetworkInteractor();
    }

    public void onClickSendShoutout(String shoutout){
        system.sendMessage(createShoutout(shoutout), new TaskCallback(){
            @Override
            public void onSuccess() {
                screen.showSendShoutoutSuccess(networkSystem.isConnected() || networkSystem.isConnecting());
            }

            @Override
            public void onFail(int errorCode) {
                screen.showError(errorCode);
            }
        });
    }

    public void onClickSendSlideRating(float rating){
        system.sendSlideRating(createRating(rating), new TaskCallback(){

            @Override
            public void onSuccess() {
                screen.showSendRatingSuccess(networkSystem.isConnected() || networkSystem.isConnecting());
            }

            @Override
            public void onFail(int errorCode) {
                screen.showError(errorCode);
            }
        });
    }

    public void onClickSendQuestion(String question, String contactInfo, boolean isPublic){
        system.sendQuestion(createQuestion(question, contactInfo, isPublic), new TaskCallback(){

            @Override
            public void onSuccess() {
                screen.showSendQuestionSuccess(networkSystem.isConnected() || networkSystem.isConnecting());
            }

            @Override
            public void onFail(int errorCode) {
                screen.showError(errorCode);
            }
        });
    }

    private Shoutout createShoutout(String message){
        Shoutout shoutout = new Shoutout();
        shoutout.setMessage(message);
        shoutout.setTime(getTimeNow());
        shoutout.setUsername(system.getUsername());
        return shoutout;
    }

    private SlideRating createRating(float rating){
        SlideRating slideRating = new SlideRating();
        slideRating.setRating(rating);
        slideRating.setTime(getTimeNow());
        slideRating.setUsername(system.getUsername());
        return slideRating;
    }

    private Question createQuestion(String questionMsg, String contactInfo, boolean isPublic){
        Question question = new Question();
        question.setQuestion(questionMsg);
        question.setContactInfo(contactInfo);
        question.setPublic(isPublic);
        question.setTime(getTimeNow());
        question.setUsername(system.getUsername());
        return question;
    }

    private String getTimeNow(){
        return DateUtil.getTimeNowMMMMddyyyyHHmmssaa();
    }
}
