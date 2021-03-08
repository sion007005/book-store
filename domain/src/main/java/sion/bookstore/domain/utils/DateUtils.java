package sion.bookstore.domain.utils;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Slf4j
public class DateUtils {
    private DateUtils() {}

    public static java.util.Date getSimpleDate(String date) {
        SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date created = null;

        try {
            created = fm.parse(date);
        } catch (ParseException e) {
            log.error(e.getMessage(), e);
        }

        return created;
    }

    public static java.util.Date getDate(String date) {
        SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date created = null;

        try {
            created = fm.parse(date);
        } catch (ParseException e) {
            log.error(e.getMessage(), e);
        }

        return created;
    }

    public static java.sql.Timestamp getTimestamp(java.util.Date date) {
        java.sql.Timestamp sqlDate = new java.sql.Timestamp((date.getTime()));
        return sqlDate;
    }
}
