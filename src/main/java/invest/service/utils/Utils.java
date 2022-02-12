package invest.service.utils;

public class Utils {

    public static Double parseDouble(String d) {
        try {
            return Double.parseDouble(d);
        } catch (Exception e) {
            return 0D;
        }
    }

}
