package sion.bookstore.domain.best_seller.repository;

import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.Date;

@Getter
@Service
public class BestSeller {
    private Long id;
    private Long categoryId;
    private Long bookId;
    private Date createdAt;
    private String createdBy;
    private Date modifiedAt;
    private String modifiedBy;
    private boolean deleted;
}
