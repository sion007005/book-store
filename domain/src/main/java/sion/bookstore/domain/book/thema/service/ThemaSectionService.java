package sion.bookstore.domain.book.thema.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sion.bookstore.domain.book.thema.repository.ThemaSection;
import sion.bookstore.domain.book.thema.repository.ThemaSectionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ThemaSectionService {
    private final ThemaSectionRepository themaSectionRepository;

    public Long create(ThemaSection themaSection) {
        return themaSectionRepository.create(themaSection);
    }

    public ThemaSection findOne(Long id) {
        return themaSectionRepository.findOne(id);
    }

    public void update(ThemaSection themaSection) {
        themaSectionRepository.update(themaSection);
    }

    public List<ThemaSection> findAll(ThemaSectionSearchCondition themaSectionSearchCondition) {
        return themaSectionRepository.findAll(themaSectionSearchCondition);
    }

}
