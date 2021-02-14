package sion.bookstore.front.controller.order;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CartForm {
    private List<CartItemForm> cartItemForms;

    @Getter
    @Setter
    public static class CartItemForm {
        private Long cartItemId;
        private Long bookId;
        private Integer quantity;
    }
}
