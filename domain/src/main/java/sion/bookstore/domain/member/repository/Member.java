package sion.bookstore.domain.member.repository;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;


@Getter
@Setter
@EqualsAndHashCode
public class Member {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String passwordSalt;
    private String phone;
    private String profileImgPath;
    private MultipartFile profileImageFile;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;
    private String createdBy;
    private Date modifiedAt;
    private String modifiedBy;
    private boolean deleted;
    private boolean isAdmin;
}
