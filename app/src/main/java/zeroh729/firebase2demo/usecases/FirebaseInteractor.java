package zeroh729.firebase2demo.usecases;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.facebook.AccessToken;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.androidannotations.annotations.EBean;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import zeroh729.firebase2demo.Firebase2Demo;
import zeroh729.firebase2demo.R;
import zeroh729.firebase2demo.interfaces.FetchCallback;
import zeroh729.firebase2demo.interfaces.TaskCallback;
import zeroh729.firebase2demo.models.MinoryContest;
import zeroh729.firebase2demo.models.MinoryContest_;
import zeroh729.firebase2demo.models.Question;
import zeroh729.firebase2demo.models.Shoutout;
import zeroh729.firebase2demo.models.SlideRating;
import zeroh729.firebase2demo.models.User;
import zeroh729.firebase2demo.models.User_;
import zeroh729.firebase2demo.usecases.accountsubscriber.interfaces.AccountSubInteractorInterface;
import zeroh729.firebase2demo.usecases.authentication.interfaces.AuthenticationInteractorInterface;
import zeroh729.firebase2demo.usecases.contentsubscriber.interfaces.ContentInteractorInterface;
import zeroh729.firebase2demo.usecases.minoritycontest.interfaces.ContestInteractorInterface;
import zeroh729.firebase2demo.usecases.navigation.interfaces.NavigationInteractorInterface;
import zeroh729.firebase2demo.usecases.sendfeedback.interfaces.SendFeedbackInteractorInterface;
import zeroh729.firebase2demo.utils.DateUtil;
import zeroh729.firebase2demo.utils.ResourceUtil;
import zeroh729.firebase2demo.values.DbConstants;
import zeroh729.firebase2demo.values.ErrorCodeConstants;

@EBean
public class FirebaseInteractor {
    public static DatabaseReference firebaseRef = FirebaseDatabase.getInstance().getReference();
    private static User_ user = User_.getInstance_(Firebase2Demo.getContext());

    public static class AuthenticationInteractor implements AuthenticationInteractorInterface {
        @Override
        public void loginWithGoogleAccount(Intent data, TaskCallback callback) {

        }

        @Override
        public void handleFbAccessToken(AccessToken accessToken, TaskCallback callback) {
            AuthCredential credential = FacebookAuthProvider.getCredential(accessToken.getToken());
            handleCredential(credential, callback);
        }

        private void handleCredential(AuthCredential credential, final TaskCallback callback){
            FirebaseAuth.getInstance().signInWithCredential(credential)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                callback.onSuccess();
                            }else{
                                callback.onFail(ErrorCodeConstants.FB_LOGIN_ERROR);
                            }
                        }
                    });
        }
    }

    public static class AccountStateInteractor implements AccountSubInteractorInterface{
        private FirebaseAuth.AuthStateListener authStateListener;

        @Override
        public void subscribeToAccountState(final AuthStateCallback callback) {
            authStateListener = new FirebaseAuth.AuthStateListener() {
                @Override
                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                    if(firebaseAuth.getCurrentUser() != null){
                        callback.onUserIsLoggedIn();
                    }else{
                        callback.onUserIsLoggedOut();
                    }
                }
            };
            FirebaseAuth.getInstance().addAuthStateListener(authStateListener);
        }

        @Override
        public void unsubscribe() {
            FirebaseAuth.getInstance().removeAuthStateListener(authStateListener);
        }

        @Override
        public User getUser() {
            return FirebaseInteractor.getUser();
        }
    }

    private static User getUser() {
        if(FirebaseAuth.getInstance().getCurrentUser() == null)
            return user;

        FirebaseUser fbuser = FirebaseAuth.getInstance().getCurrentUser();
        user.setId(fbuser.getUid());
        user.setName(fbuser.getDisplayName());
        user.setContactInfo(fbuser.getEmail());
        user.setPictureUrl(fbuser.getPhotoUrl().toString());
        return user;
    }

    public static class NavigationInteractor implements NavigationInteractorInterface{

        @Override
        public User getUser() {
            return FirebaseInteractor.getUser();
        }
    }

    public static class SendFeedbackInteractor  implements SendFeedbackInteractorInterface {
        @Override
        public void sendMessage(Shoutout message, final TaskCallback taskCallback) {
            HashMap map = new HashMap();
            map.put(DbConstants.COL_USER, getUser().getId());
            map.put(DbConstants.COL_USERNAME, message.getUsername());
            map.put(DbConstants.COL_TIME, message.getTime());
            map.put(DbConstants.COL_MESSAGE, message.getMessage());
            firebaseRef.child(DbConstants.COL_SHOUTOUT).push().setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful())
                        taskCallback.onSuccess();
                    else
                        taskCallback.onFail(0);
                }
            });
        }

        @Override
        public String getUsername() {
            return getUser().getName();
        }

        @Override
        public void sendQuestion(Question question, final TaskCallback callback) {
            HashMap map = new HashMap();
            map.put(DbConstants.COL_USER, getUser().getId());
            map.put(DbConstants.COL_USERNAME, question.getUsername());
            map.put(DbConstants.COL_TIME, question.getTime());
            map.put(DbConstants.COL_MESSAGE, question.getQuestion());
            map.put(DbConstants.COL_CONTACTINFO, question.getContactInfo());
            map.put(DbConstants.COL_ISPUBLIC, question.isPublic());
            firebaseRef.child(DbConstants.COL_QNA).push().setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful())
                        callback.onSuccess();
                    else
                        callback.onFail(0);
                }
            });
        }

        @Override
        public void sendSlideRating(SlideRating rating, TaskCallback callback) {

        }
    }

    public static class ContentInteractor implements ContentInteractorInterface {

        @Override
        public void subscribeToShoutouts(final FetchCallback fetchCallback) {
            firebaseRef.child(DbConstants.COL_SHOUTOUT).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    HashMap<String, String> map = (HashMap)dataSnapshot.getValue();
                    fetchCallback.onSuccess(map);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            firebaseRef.child(DbConstants.COL_SHOUTOUT).keepSynced(true);
        }

        @Override
        public Shoutout getShoutout(HashMap<String, String> data) {
            Shoutout shoutout = new Shoutout();
            shoutout.setUsername(data.get(DbConstants.COL_USERNAME));
            shoutout.setTime(data.get(DbConstants.COL_TIME));
            shoutout.setMessage(data.get(DbConstants.COL_MESSAGE));
            return shoutout;
        }

    }

    @EBean
    public static class ContestInteractor implements ContestInteractorInterface{
        MinoryContest_ contestData = MinoryContest_.getInstance_(Firebase2Demo.getContext());

        @Override
        public void subscribeToContestData(final TaskCallback callback) {
            firebaseRef.child(DbConstants.COL_RESPONSES).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    addResponseCount(true, dataSnapshot);
                    if(getUser().getId() != null)
                        if(dataSnapshot.getKey().equals(getUser().getId()))
                            contestData.setUserAnswer((String)((HashMap)dataSnapshot.getValue()).get(DbConstants.COL_ANSWER));
                    callback.onSuccess();
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    changeResponseCount(dataSnapshot);
                    callback.onSuccess();
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    addResponseCount(false, dataSnapshot);
                    callback.onSuccess();
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            firebaseRef.child(DbConstants.COL_GAMERUNNING).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    contestData.setGameRunning((boolean)dataSnapshot.getValue());
                    callback.onSuccess();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            firebaseRef.child(DbConstants.COL_QUESTION).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    contestData.setQuestion((String)dataSnapshot.getValue());
                    callback.onSuccess();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            firebaseRef.child(DbConstants.COL_CHOICES).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String a = (String) dataSnapshot.child(DbConstants.COL_A).getValue();
                    String b = (String) dataSnapshot.child(DbConstants.COL_B).getValue();
                    String c = (String) dataSnapshot.child(DbConstants.COL_C).getValue();
                    String d = (String) dataSnapshot.child(DbConstants.COL_D).getValue();
                    contestData.setChoiceA(a);
                    contestData.setChoiceB(b);
                    contestData.setChoiceC(c);
                    contestData.setChoiceD(d);
                    callback.onSuccess();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            firebaseRef.child(DbConstants.COL_RESPONSES).keepSynced(true);
            firebaseRef.child(DbConstants.COL_GAMERUNNING).keepSynced(true);
            firebaseRef.child(DbConstants.COL_QUESTION).keepSynced(true);
            firebaseRef.child(DbConstants.COL_CHOICES).keepSynced(true);
        }

        @Override
        public MinoryContest getContestData() {
            return contestData;
        }

        private void changeResponseCount(DataSnapshot data){
            String previousAnswer = contestData.getResponses().get(data.getKey());
                switch (previousAnswer){
                    case "A":
                        contestData.setA(contestData.getA() - 1);
                        break;
                    case "B":
                        contestData.setB(contestData.getB() - 1);
                        break;
                    case "C":
                        contestData.setC(contestData.getC() - 1);
                        break;
                    case "D":
                        contestData.setD(contestData.getD() - 1);
                        break;
                }

            updateResponses(data);

            HashMap<String, String> map = (HashMap)data.getValue();
            switch (map.get(DbConstants.COL_ANSWER)){
                case "A":
                    contestData.setA(contestData.getA() + 1);
                    break;
                case "B":
                    contestData.setB(contestData.getB() + 1);
                    break;
                case "C":
                    contestData.setC(contestData.getC() + 1);
                    break;
                case "D":
                    contestData.setD(contestData.getD() + 1);
                    break;
            }
        }

        private void addResponseCount(boolean isAdd, DataSnapshot data){
            updateResponses(data);

            HashMap<String, String> map = (HashMap)data.getValue();
            switch (map.get(DbConstants.COL_ANSWER)){
                case "A":
                    contestData.setA(isAdd ? contestData.getA() + 1 : contestData.getA() - 1);
                    break;
                case "B":
                    contestData.setB(isAdd ? contestData.getB() + 1 : contestData.getB() - 1);
                    break;
                case "C":
                    contestData.setC(isAdd ? contestData.getC() + 1 : contestData.getC() - 1);
                    break;
                case "D":
                    contestData.setD(isAdd ? contestData.getD() + 1 : contestData.getD() - 1);
                    break;
            }
        }

        private void updateResponses(DataSnapshot data){
            HashMap<String, String> map = (HashMap)data.getValue();
            contestData.getResponses().put(data.getKey(), map.get(DbConstants.COL_ANSWER));
        }

        @Override
        public void saveAnswerLocally(String answer) {
//            pref.answerToMinorityContest().put(answer);
            contestData.setUserAnswer(answer);
            SharedPreferences sharedPref = Firebase2Demo.getContext().getSharedPreferences(
                    ResourceUtil.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
            sharedPref.edit().putString("answerToMinority", answer);
            sharedPref.edit().commit();
        }

        @Override
        public void clearAnswerLocally() {
            contestData.setUserAnswer("");
            SharedPreferences sharedPref = Firebase2Demo.getContext().getSharedPreferences(
                    ResourceUtil.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
            sharedPref.edit().putString("answerToMinority", "");
            sharedPref.edit().commit();
        }

        @Override
        public String readLocalAnswer() {
            SharedPreferences sharedPref = Firebase2Demo.getContext().getSharedPreferences(
                    ResourceUtil.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
            contestData.setUserAnswer( sharedPref.getString("answerToMinority", ""));
            return contestData.getUserAnswer();
        }

        @Override
        public void uploadNoAnswer() {
            if(getUser().getId() != null) {
                HashMap<String, String> map = new HashMap<>();
                map.put(DbConstants.COL_ANSWER, "");
                map.put(DbConstants.COL_TIME, DateUtil.getTimeNowMMMMddyyyyHHmmssaa());
                map.put(DbConstants.COL_USERNAME, getUser().getName());
                firebaseRef.child(DbConstants.COL_RESPONSES).child(getUser().getId()).setValue(map);
            }
        }

        @Override
        public boolean isGameRunning() {
            return contestData.isGameRunning();
        }

        @Override
        public void uploadAnswer(String answer) {
            HashMap<String, String> map = new HashMap<>();
            map.put(DbConstants.COL_ANSWER, answer);
            map.put(DbConstants.COL_TIME, DateUtil.getTimeNowMMMMddyyyyHHmmssaa());
            if(getUser().getId() != null) {
                map.put(DbConstants.COL_USERNAME, getUser().getName());
                firebaseRef.child(DbConstants.COL_RESPONSES).child(getUser().getId()).setValue(map);
            }else{
                map.put(DbConstants.COL_USERNAME, "anon");
                firebaseRef.child(DbConstants.COL_RESPONSES).push().setValue(map);
            }
        }
    }
}
