package sion.bookstore.domain.category.repository;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import sion.bookstore.domain.BaseAudit;

import java.util.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Category extends BaseAudit {
    private Long id;
    private Long parentId;
    private String name;
    private Integer level;
    private Integer order;
    private String link;
    private Date createdAt;
    private String createdBy;
    private Date modifiedAt;
    private String modifiedBy;
    private boolean deleted;
}
