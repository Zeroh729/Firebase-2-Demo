package zeroh729.firebase2demo.interfaces;

import java.util.HashMap;

public interface FetchCallback {
    void onSuccess(HashMap data);
    void onFail(int errorCode);
}
