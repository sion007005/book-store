package sion.bookstore.domain.book.thema.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sion.bookstore.domain.book.thema.repository.ThemaBookRepository;

@Service
@RequiredArgsConstructor
public class ThemaBookService {
    private final ThemaBookRepository themaBookRepository;


}
