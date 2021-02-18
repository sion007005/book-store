package sion.bookstore.domain.utils.validator;

import org.springframework.stereotype.Component;
import sion.bookstore.domain.book.service.SaleStatus;

@Component
public class SaleStatusValidator implements Validator<SaleStatus> {
    @Override
    public void validate(SaleStatus status, String type) {
        if (status != SaleStatus.IN_STOCK) {
            throw new ValidationException("구매할 수 없는 상품입니다.");
        }
    }
}
