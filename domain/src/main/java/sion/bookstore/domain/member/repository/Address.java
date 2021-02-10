package sion.bookstore.domain.member.repository;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Address {
    private Long id;
    private Long memberId;
    private String name;
    private String addressBasic;
    private String addressDetail;
    private Integer zipCode;
    private Date createdAt;
    private String createdBy;
    private Date modifiedAt;
    private String modifiedBy;
    private boolean deleted;
}
