package sion.bookstore.domain.category.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sion.bookstore.domain.parser.ParsedCategory;
import sion.bookstore.domain.parser.Yes24CategoryParser;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryRegisterFacade {
    private final CategoryService categoryService;
    private final Yes24CategoryParser yes24CategoryParser;

    public void register(Yes24CategoryParser.CategoryNumber categoryNumber) throws IOException {
        List<ParsedCategory> parsedCategories = yes24CategoryParser.parse(categoryNumber);
        categoryService.createAllByParsedList(parsedCategories);
    }

}
