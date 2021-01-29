package sion.bookstore.admin.controller.util.validator;

public interface Validator<T> {
    public void validate(T content);
}
