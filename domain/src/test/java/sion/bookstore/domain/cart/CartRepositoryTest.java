package sion.bookstore.domain.cart;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import sion.bookstore.domain.ApplicationConfiguration;
import sion.bookstore.domain.cart.repository.CartItem;
import sion.bookstore.domain.cart.repository.CartRepository;

import java.util.Date;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest(classes={ApplicationConfiguration.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CartRepositoryTest {
    @Autowired
    private CartRepository cartRepository;

    @Test
    public void 장바구니_상품추가() {
        CartItem cartItem = new CartItem();
        cartItem.setUserId(1L);
        cartItem.setBookId(800L);
        cartItem.setQuantity(1);
        cartItem.setCreatedAt(new Date());
        cartItem.setCreatedBy("user");
        cartItem.setModifiedAt(new Date());
        cartItem.setModifiedBy("user");
        cartItem.setDeleted(false);

        cartRepository.add(cartItem);

        Assertions.assertEquals(2, cartItem.getId());
    }

    @Test
    public void 특정회원의_장바구니_상품목록_가져오기() {
        List<CartItem> cartItems = cartRepository.getCartItems(1L);
        for (CartItem cartItem : cartItems) {
            log.info("장바구니에 넣은 책 제목:{}", cartItem.getBook().getTitle());
            log.info("선택한 수량: {}", cartItem.getQuantity());
        }

        Assertions.assertEquals(2, cartItems.size());
    }
}
