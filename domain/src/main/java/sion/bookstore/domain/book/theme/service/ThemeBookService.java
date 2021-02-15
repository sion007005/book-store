package sion.bookstore.domain.book.theme.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sion.bookstore.domain.book.theme.repository.ThemeBookRepository;

@Service
@RequiredArgsConstructor
public class ThemeBookService {
    private final ThemeBookRepository themeBookRepository;


}
