package zeroh729.firebase2demo.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static String getTimeNowMMMMddyyyyHHmmssaa(){
        DateFormat format = new SimpleDateFormat("MMMM dd, yyyy HH:mm:ss aa");
        return format.format(new Date());
    }
}
