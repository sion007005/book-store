package sion.bookstore.domain.payment.repository;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import sion.bookstore.domain.BaseAudit;

import java.util.Date;

@Getter
@Setter
public class Payment extends BaseAudit {
    private Long id;
    private Long memberId;
    private Long orderId;
    private PaymentType paymentType;
    private String virtualAccount;
    private String approvalNumber;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;
    private String createdBy;
    private Date modifiedAt;
    private String modifiedBy;
    private boolean deleted;
}
