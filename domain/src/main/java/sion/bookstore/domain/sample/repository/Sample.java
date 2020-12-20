package sion.bookstore.domain.sample.repository;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Sample {
    private Long id;
    private String name;
    private String email;
    private String description;
    private Date createdAt;
    private String createdBy;
    private Date modifiedAt;
    private String modifiedBy;
    private boolean deleted;
}