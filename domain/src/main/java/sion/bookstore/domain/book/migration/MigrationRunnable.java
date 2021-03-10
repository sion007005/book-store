package sion.bookstore.domain.book.migration;

import org.springframework.data.domain.Page;
import sion.bookstore.domain.book.repository.Book;
import sion.bookstore.domain.book.service.BookSearchCondition;

import java.util.List;

public class MigrationRunnable implements Runnable {
    private BookSearchCondition condition = new BookSearchCondition();
    private BookMigrationService bookMigrationService;
    public static final int UNIT_SIZE = 100;
    private int executionGroupCount;
    private int startIndex;

    public MigrationRunnable(int page, int totalSize, BookMigrationService bookMigrationService) {
        condition.setSize(UNIT_SIZE);

        this.startIndex = (page - 1) * totalSize;
        this.executionGroupCount = totalSize / UNIT_SIZE;
        this.bookMigrationService = bookMigrationService;
    }

    @Override
    public void run() {
        for (int i = 1; i <= executionGroupCount; i++) {
            setSearchStartIndex(i);

            Page<Book> bookPage = bookMigrationService.getBookPage(condition);
            List<Book> setBookList = bookMigrationService.setAuthorAndTranslator(bookPage);
            bookMigrationService.updateBookList(setBookList);
        }
    }

    private void setSearchStartIndex(int executionGroupCount) {
        if (executionGroupCount != 1) {
            startIndex += UNIT_SIZE;
        }
        condition.setSearchStartIndex(startIndex);
    }
}
