package sion.bookstore.domain.member.repository;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@EqualsAndHashCode
public class Member {
    private Integer id;
    private String name;
    private String email;
    private String password;
    private String passwordSalt;
    private String phone;
    private String profileImgPath;
    private Date createdAt;
    private String createdBy;
    private Date modifiedAt;
    private String modifiedBy;
    private boolean deleted;
}