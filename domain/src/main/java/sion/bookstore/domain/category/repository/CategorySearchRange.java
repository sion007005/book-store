package sion.bookstore.domain.category.repository;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class CategorySearchRange {
    private Long parentId;
    private Integer start;
    private Integer end;
    private Boolean orderChanged;
    private Boolean orderChangedUp;

    public CategorySearchRange(Long parentId, Integer current, Integer changeTo) {
        this.parentId = parentId;
        this.start = current;
        this.end = changeTo;
        checkOrderChange();
    }

    public CategorySearchRange(Long parentId, Integer current) {
        this.parentId = parentId;
        this.start = current;
    }

    private void checkOrderChange() {
        Integer diff = (start - end);

        if (diff == 0) {
            this.orderChanged = false;
            this.orderChangedUp = false;
        }

        if (diff > 0) {
            this.orderChanged = true;
            this.orderChangedUp = false;
        }

        if (diff < 0) {
            this.orderChanged = true;
            this.orderChangedUp = true;
        }
    }

    public boolean needPlusOrder() {
        if (Objects.isNull(end)) {
            return true;
        }

        if (orderChangedUp) {
            return false;
        }

        return false;
    }
}
