package sion.bookstore.domain.member.repository;

import lombok.Getter;
import lombok.Setter;
import sion.bookstore.domain.BaseAudit;

import java.util.Date;

@Getter
@Setter
public class Address extends BaseAudit {
    private Long id;
    private Long memberId;
    private String name;
    private String addressBasic;
    private String addressDetail;
    private Integer zipCode;
    private boolean defaultAddress;

    private Date createdAt;
    private String createdBy;
    private Date modifiedAt;
    private String modifiedBy;
    private boolean deleted;
}
