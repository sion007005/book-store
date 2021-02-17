package sion.bookstore.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class BaseAudit {
    private String email;
    private Long memberId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;
    private String createdBy;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date modifiedAt;
    private String modifiedBy;
    private boolean deleted;
}
