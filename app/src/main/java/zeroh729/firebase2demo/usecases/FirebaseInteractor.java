package zeroh729.firebase2demo.usecases;

import android.content.Intent;

import java.util.ArrayList;
import java.util.HashMap;

import zeroh729.firebase2demo.interfaces.DoneCallback;
import zeroh729.firebase2demo.interfaces.FetchCallback;
import zeroh729.firebase2demo.interfaces.TaskCallback;
import zeroh729.firebase2demo.models.MinoryContest;
import zeroh729.firebase2demo.models.Question;
import zeroh729.firebase2demo.models.Shoutout;
import zeroh729.firebase2demo.models.SlideRating;
import zeroh729.firebase2demo.models.User;
import zeroh729.firebase2demo.usecases.authentication.interfaces.AuthenticationInteractorInterface;
import zeroh729.firebase2demo.usecases.contentsubscriber.interfaces.ContentInteractorInterface;
import zeroh729.firebase2demo.usecases.minoritycontest.interfaces.ContestInteractorInterface;
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
        public void subscribeToShoutouts(FetchCallback fetchCallback) {

        }

        @Override
        public Shoutout getShoutout(HashMap data) {
            return null;
        }

    }

    public static class ContestInteractor implements ContestInteractorInterface{

        @Override
        public void subscribeToContestData(FetchCallback callback) {

        }

        @Override
        public MinoryContest getContestData(HashMap data) {
            return null;
        }

        @Override
        public void saveAnswerLocally(String answer) {

        }


        @Override
        public void clearAnswerLocally() {

        }

        @Override
        public void uploadNoAnswer() {

        }

        @Override
        public boolean isGameRunning(HashMap data) {
            return false;
        }

        @Override
        public void uploadAnswer(String answer) {

        }
    }
}
