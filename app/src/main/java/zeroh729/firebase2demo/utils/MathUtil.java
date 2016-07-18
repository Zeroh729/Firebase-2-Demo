package zeroh729.firebase2demo.utils;

public class MathUtil {
    public static int calculateParcentage(int value, int total){
        int percentage = 0;
        if(total != 0)
            percentage = (value * 100) / total;
        return percentage;
    }
}
