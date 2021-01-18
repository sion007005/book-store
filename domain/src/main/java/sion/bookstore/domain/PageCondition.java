package sion.bookstore.domain;

import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Setter
public class PageCondition {
    private int page;
    private int size = 5; //한 페이지는 다섯개의 책을 보여주도록 디폴트 값 설정
    private int searchStartIndex; // 데이터를 꺼내올 때, 시작 인덱스

    public int getPage() {
        return (page == 0) ? 1 : page;
    }
    //현재 페이지 구하기
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
        return getZeroBasePage() * size;
    }
}
