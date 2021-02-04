package sion.bookstore.domain.cart;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import sion.bookstore.domain.ApplicationConfiguration;
import sion.bookstore.domain.cart.repository.CartRepository;

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
        // TODO 테스트 완성하기
//        CartItem cartItem = new CartItem();
//        cartItem.setUserId(1L);
//        cartItem.setBookId(1L);
//        cartItem.setQuantity(3);
//        cartItem.setCreatedAt(new Date());
//        cartItem.setCreatedBy("user");
//        cartItem.setModifiedAt(new Date());
//        cartItem.setModifiedBy("user");
//        cartItem.setDeleted(false);
//
//        cartRepository.add(cartItem);
//
//        Assertions.assertEquals(1, cartItem.getId());
    }

}
