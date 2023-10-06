package utils;

public class StringUtils {

    private StringUtils(){}
    public static String getRandomEmailId() {
        return "api" + System.currentTimeMillis() + "@api.com";
    }
}
