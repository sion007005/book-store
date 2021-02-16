package sion.bookstore.domain.email;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Mail {
    private String address;
    private String title;
    private String message;
}
