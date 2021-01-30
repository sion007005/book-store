package sion.bookstore.domain.utils.validator;

public interface Validator<T> {
    void validate(T content, String type);
}
