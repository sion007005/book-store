package sion.bookstore.domain.utils;

public class NumberUtils {
    private NumberUtils() {}

    public static Integer parseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            return null;
        }
    }

    public static Integer parseInt(String value, Integer defaultValue) {
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static Long parseLong(String value) {
        try {
            return Long.parseLong(value);
        } catch (Exception e) {
            return  null;
        }
    }

    public static Integer removeComma(String value) {
        try {
            return Integer.parseInt(value.replace(",", ""));
        } catch (Exception e) {
            return null;
        }
    }
}
