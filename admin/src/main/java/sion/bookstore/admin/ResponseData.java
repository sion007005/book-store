package sion.bookstore.admin;

import lombok.Getter;

@Getter
public class ResponseData {
    private boolean status;
    private String errorMessage;
    private Object data;

    public ResponseData(boolean status, Object data) {
        this.status = status;
        this.data = data;
    }

    public ResponseData(boolean status, String errorMessage) {
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
