package sion.bookstore.front;

import lombok.Getter;

@Getter
public class ResponseData {
    private boolean status;
    private String errorMessage;
    private Object data;

    private ResponseData(boolean status, Object data) {
        this.status = status;
        this.data = data;
    }

    private ResponseData(boolean status, String errorMessage) {
        this.status = status;
        this.errorMessage = errorMessage;
    }

    public static ResponseData success(Object data) {
        return new ResponseData(true, data);
    }

    public static ResponseData fail(String errorMessage) {
        return new ResponseData(false, errorMessage);
    }
}
