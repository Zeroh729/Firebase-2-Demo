package zeroh729.firebase2demo.utils;

import zeroh729.firebase2demo.interfaces.DoneCallback;

public class ThreadUtil {
    public static void displayForXSeconds(final long time, final DoneCallback callback){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                callback.onFinish();
            }
        }).run();
    }
}
