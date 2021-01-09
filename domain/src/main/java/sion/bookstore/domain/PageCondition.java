package sion.bookstore.domain;

import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Setter
public class PageCondition {
    private int page;
    private int size = 100;

    public int getPage() {
        return (page == 0)? 1 : page;
    }

    public int getZeroBasePage() {
        return getPage() - 1;
    }

    public int getSize() {
        return this.size;
    }

    public Pageable getPageable() {
        return PageRequest.of(getZeroBasePage(), getSize());
    }
}
