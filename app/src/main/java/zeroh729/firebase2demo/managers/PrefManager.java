package zeroh729.firebase2demo.managers;

import org.androidannotations.annotations.sharedpreferences.DefaultInt;
import org.androidannotations.annotations.sharedpreferences.DefaultString;
import org.androidannotations.annotations.sharedpreferences.SharedPref;

@SharedPref
public interface PrefManager{
    @DefaultString("")
    String answerToMinorityContest();
}
