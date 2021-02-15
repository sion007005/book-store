package sion.bookstore.domain.member.repository;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;
import sion.bookstore.domain.BaseAudit;

import java.util.Date;
import java.util.List;


@Getter
@Setter
@EqualsAndHashCode
public class Member extends BaseAudit {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String passwordSalt;
    private String phone;
    private String profileImgPath;
    private boolean admin;
    private MultipartFile profileImageFile;
    private List<Address> addressList;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;
    private String createdBy;
    private Date modifiedAt;
    private String modifiedBy;
    private boolean deleted;
}
