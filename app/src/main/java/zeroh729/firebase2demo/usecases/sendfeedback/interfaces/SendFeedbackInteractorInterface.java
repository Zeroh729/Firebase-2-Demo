package zeroh729.firebase2demo.usecases.sendfeedback.interfaces;

import zeroh729.firebase2demo.interfaces.TaskCallback;
import zeroh729.firebase2demo.models.Question;
import zeroh729.firebase2demo.models.Shoutout;
import zeroh729.firebase2demo.models.SlideRating;

public interface SendFeedbackInteractorInterface {

    void sendMessage(Shoutout message, TaskCallback taskCallback);

    void sendSlideRating(int rating);

    void sendQuestion(String question, String contactInfo, boolean isPublic);

    String getUsername();

    void sendQuestion(Question question, TaskCallback callback);

    void sendSlideRating(SlideRating rating, TaskCallback callback);
}
