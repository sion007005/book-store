package sion.bookstore.domain.utils;

import org.springframework.stereotype.Component;

@Component
public class ExcludeSpecialCharacter {
    public String getString(String word) {
        String match = "[\\{\\}\\[\\]\\/?.,;:|\\)*~`!^\\-+<>@\\#$%&\\\\\\=\\(\\'\\\"]";
        String replaced = word.replaceAll(match, "");

        return replaced;
    }
}
