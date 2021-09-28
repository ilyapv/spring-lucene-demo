package com.cor.demo.jpa.manager;

import com.cor.demo.jpa.Application;
import com.cor.demo.jpa.entity.Book;
import com.cor.demo.jpa.entity.BookCategory;
import com.cor.demo.jpa.entity.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertEquals;

/**
 * DBUnit Test - loads data into the database to run tests against the
 * BookManager. More thorough (and ultimately easier in this context) than using mocks.
 */
@Slf4j
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {Application.class})
public class BookManagerDBUnitTest {

    /**
     * Book Manager Under Test.
     */
    @Autowired
    private BookManager bookManager;

    @Autowired
    private BookRepository repository;

    @BeforeEach
    public void setup() {
        repository.save(new Book("The Lord of the Rings", BookCategory.FANTASY, "the Lord of the Rings is an epic high fantasy novel written by English philologist and University of Oxford professor J. R. R. Tolkien"));
        repository.save(new Book("The War of the Worlds", BookCategory.FANTASY, "War in space"));
        repository.save(new Book("Apollo 13", BookCategory.SCIFI, "Apollo 13 was the seventh manned mission in the American Apollo space program and the third intended to land on the Moon"));
        repository.save(new Book("2001: A Space Oddysey", BookCategory.SCIFI, "2001: A Space Odyssey is a 1968 British-American science fiction film produced and directed by Stanley Kubrick"));
        repository.save(new Book("Dune", BookCategory.SCIFI, "Dune is a 1984 science fiction film written and directed by David Lynch, based on the 1965 Frank Herbert novel of the same name."));
    }

    @AfterEach
    public void tearDown() {
        deleteBooks();
    }

    @Test
    @DisplayName("Should return results for searching for 'Space' in SCF-FI books")
    public void testSciFiBookSearch() throws Exception {

        bookManager.listAllBooks();
        bookManager.updateFullTextIndex();
        List<Book> results = bookManager.search(BookCategory.SCIFI, "Space");

        assertEquals("Expected 2 results for SCI FI search for 'Space'", 2, results.size());
        assertEquals("Expected 1st result to be '2001: A Space Oddysey'", "2001: A Space Oddysey", results.get(0).getTitle());
        assertEquals("Expected 2nd result to be 'Apollo 13'", "Apollo 13", results.get(1).getTitle());
    }

    private void deleteBooks() {
        log.info("Deleting Books...-");
        bookManager.deleteAllBooks();
    }

}
