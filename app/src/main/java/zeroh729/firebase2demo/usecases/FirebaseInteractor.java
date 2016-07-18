package zeroh729.firebase2demo.usecases;

import android.content.Intent;

import java.util.ArrayList;
import java.util.HashMap;

import zeroh729.firebase2demo.interfaces.FetchCallback;
import zeroh729.firebase2demo.interfaces.TaskCallback;
import zeroh729.firebase2demo.models.Question;
import zeroh729.firebase2demo.models.Shoutout;
import zeroh729.firebase2demo.models.SlideRating;
import zeroh729.firebase2demo.models.User;
import zeroh729.firebase2demo.usecases.authentication.interfaces.AuthenticationInteractorInterface;
import zeroh729.firebase2demo.usecases.contentsubscriber.interfaces.ContentInteractorInterface;
import zeroh729.firebase2demo.usecases.navigation.interfaces.NavigationInteractorInterface;
import zeroh729.firebase2demo.usecases.sendfeedback.interfaces.SendFeedbackInteractorInterface;

public class FirebaseInteractor {

    public static class AuthenticationInteractor implements AuthenticationInteractorInterface {
        @Override
        public void loginWithGoogleAccount(Intent data, TaskCallback callback) {

        }
    }

    public static class NavigationInteractor implements NavigationInteractorInterface{

        @Override
        public User getUser() {
            return null;
        }
    }

    public static class SendFeedbackInteractor  implements SendFeedbackInteractorInterface {
        @Override
        public void sendMessage(Shoutout message, TaskCallback taskCallback) {

        }

        @Override
        public void sendSlideRating(int rating) {

        }

        @Override
        public void sendQuestion(String question, String contactInfo, boolean isPublic) {

        }

        @Override
        public String getUsername() {
            return null;
        }

        @Override
        public void sendQuestion(Question question, TaskCallback callback) {

        }

        @Override
        public void sendSlideRating(SlideRating rating, TaskCallback callback) {

        }
    }

    public static class ContentInteractor implements ContentInteractorInterface {

        @Override
        public void subscribeToCurrentSlideNumber(FetchCallback callback) {

        }

        @Override
        public int getSlideNumber(HashMap data) {
            return 0;
        }

        @Override
        public void subscribeToShoutouts(FetchCallback fetchCallback) {

        }

        @Override
        public ArrayList<Shoutout> getShoutout(HashMap data) {
            return null;
        }
    }
}
