package sion.bookstore.domain;

import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Objects;

@Setter
public class PageCondition {
    public static final int GET_ALL = 100000;
    private int page;
    private int size = 5; //한 페이지는 다섯개의 책을 보여주도록 디폴트 값 설정
    private Integer searchStartIndex; // 데이터를 꺼내올 때, 시작 인덱스

    public int getPage() {
        return (page == 0) ? 1 : page;
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

    public int getSearchStartIndex() {
        if (Objects.isNull(searchStartIndex)) {
            return getZeroBasePage() * size;
        }

        return searchStartIndex;
    }
}
