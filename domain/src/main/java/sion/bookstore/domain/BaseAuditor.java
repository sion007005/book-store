package sion.bookstore.domain;

import sion.bookstore.domain.auth.UserContext;

import java.util.Date;
import java.util.Objects;

public class BaseAuditor {
    public static void setCreationInfo(BaseAudit baseAudit) {
        String memberEmail = UserContext.get().getUserEmail();

        if (Objects.isNull(memberEmail)) {
            memberEmail = baseAudit.getEmail();
        }

        baseAudit.setCreatedAt(new Date());
        baseAudit.setCreatedBy(memberEmail);
        baseAudit.setModifiedAt(new Date());
        baseAudit.setModifiedBy(memberEmail);
        baseAudit.setDeleted(false);
    }

    public static void setUpdatingInfo(BaseAudit baseAudit) {
        baseAudit.setModifiedAt(new Date());
        baseAudit.setModifiedBy(UserContext.get().getUserEmail());
        baseAudit.setDeleted(false);
    }

    public static void setDeletionInfo(BaseAudit baseAudit) {
        baseAudit.setModifiedAt(new Date());
        baseAudit.setModifiedBy(UserContext.get().getUserEmail());
        baseAudit.setDeleted(true);
    }
}
