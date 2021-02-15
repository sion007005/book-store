package sion.bookstore.domain;

import sion.bookstore.domain.auth.UserContext;

import java.util.Date;

public class BaseAuditor {
    public static void setCreationInfo(BaseAudit baseAudit) {
        baseAudit.setCreatedAt(new Date());
        baseAudit.setCreatedBy(UserContext.get().getUserEmail());
        baseAudit.setModifiedAt(new Date());
        baseAudit.setModifiedBy(UserContext.get().getUserEmail());
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
