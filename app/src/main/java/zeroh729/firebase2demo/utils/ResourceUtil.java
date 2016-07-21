package zeroh729.firebase2demo.utils;

import android.content.Context;

import zeroh729.firebase2demo.Firebase2Demo;

public class ResourceUtil {
    public static Context getContext(){
        return Firebase2Demo.getContext();
    }

    public static String getString(int resId){
        return getContext().getResources().getString(resId);
    }
}
