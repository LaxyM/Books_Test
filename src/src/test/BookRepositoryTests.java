package src.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.model.Book;
import src.repository.BookRepository;
import src.util.MyList;
import static org.junit.jupiter.api.Assertions.*;

public class BookRepositoryTests {

    MyList<Book> books;
    private BookRepository bookRepository;


    @BeforeEach
    public void setUp() {
        bookRepository = new BookRepository();
    }

    @Test
    public void testAddBook() {
        bookRepository.addBook("Great Expectations", "Charles Dickens");
        assertTrue(bookRepository.isBookExist("Great Expectations", "Charles Dickens"));
        assertFalse(bookRepository.isBookExist("", ""));
        assertFalse(bookRepository.isBookExist("Great Expectations", ""));
        assertFalse(bookRepository.isBookExist("", "Charles Dickens"));
        assertFalse(bookRepository.isBookExist(null, null));
        assertFalse(bookRepository.isBookExist(null, "Charles Dickens"));
        assertFalse(bookRepository.isBookExist("Great Expectations", null));
    }

    @Test
    public void testFindBookId() {
        assertEquals("Sherlock Holmes", bookRepository.findBookId(1).getNameBook());
        assertEquals("The Catcher in the Rye", bookRepository.findBookId(5).getNameBook());
        assertNull(bookRepository.findBookId(6));
    }

    @Test
    public void testIsBookExist() {
        assertTrue(bookRepository.isBookExist("The Catcher in the Rye", "J.D. Salinger"));
        assertFalse(bookRepository.isBookExist("Great Expectations", "Charles Dickens"));
        assertFalse(bookRepository.isBookExist(null, "J.D. Salinger"));
        assertFalse(bookRepository.isBookExist("Great Expectations", null));
        assertFalse(bookRepository.isBookExist(null, null));
    }

    @Test
    public void testSearchBookByName() {
        books = bookRepository.searchBookByName("Sherlock Holmes");
        assertEquals(1, books.size());
        assertEquals("Sherlock Holmes", books.get(0).getNameBook());
        assertTrue(bookRepository.isBookExist("Sherlock Holmes", "Arthur Conan Doyle"));

        books = bookRepository.searchBookByName("SHERLOCK HOLMES");
        assertEquals(1, books.size());
        assertEquals("Sherlock Holmes", books.get(0).getNameBook());

        books = bookRepository.searchBookByName("sherlock holmes");
        assertEquals(1, books.size());
        assertEquals("Sherlock Holmes", books.get(0).getNameBook());

        books = bookRepository.searchBookByName("Harry Potter");
        assertTrue(books.isEmpty());

        books = bookRepository.searchBookByName("sh");
        assertEquals(1, books.size());
        assertEquals("Sherlock Holmes", books.get(0).getNameBook());
    }

    @Test
    public void testSearchBookByAuthor() {
        books = bookRepository.searchBookByAuthor("Harper Lee");
        assertEquals(1, books.size());
        assertEquals("Harper Lee", books.get(0).getNameAuthor());
        assertTrue(bookRepository.isBookExist("To Kill a Mockingbird", "Harper Lee"));

        books = bookRepository.searchBookByAuthor("HARPER LEE");
        assertEquals(1, books.size());
        assertEquals("Harper Lee", books.get(0).getNameAuthor());

        books = bookRepository.searchBookByAuthor("harper lee");
        assertEquals(1, books.size());
        assertEquals("Harper Lee", books.get(0).getNameAuthor());

        books = bookRepository.searchBookByAuthor("ha");
        assertEquals(1, books.size());
        assertEquals("Harper Lee", books.get(0).getNameAuthor());

        books = bookRepository.searchBookByAuthor("J. Rowling");
        assertTrue(books.isEmpty());
    }

    @Test
    public void testMarkBookUnAvailable() {
        bookRepository.markBookUnAvailable(1);
        Book book = bookRepository.findBookId(1);
        assertNotNull(book);
        assertFalse(book.isAvailable());
    }

    @Test
    public void testMarkBookAvailable() {
        bookRepository.markBookAvailable(1);
        Book book = bookRepository.findBookId(1);
        assertNotNull(book);
        assertTrue(book.isAvailable());
    }

    @Test
    public void testRemoveBook() {
        bookRepository.removeBook(1);
        assertFalse(bookRepository.isBookExist("Sherlock Holmes", "Arthur Conan Doyle"));
    }


}
