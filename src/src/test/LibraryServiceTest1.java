package src.test;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import src.model.Book;
import src.model.Reader;
import src.repository.BookRepository;
import src.repository.ReaderRepository;
import src.service.LibraryService;
import src.service.ReaderService;
import src.util.MyArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class LibraryServiceTest1 {
    private LibraryService libraryService;
    private BookRepository bookRepository;

    @BeforeEach
    public void setUp() {
        // Подготовка данных для тестирования
        BookRepository bookRepository = new BookRepository();
        libraryService = new LibraryService(bookRepository);
    }

    @ParameterizedTest
    @CsvSource({"New Book 1, New Author 1", "New Book 2, New Author 2"})
    public void testAddBook(String nameBook, String nameAuthor) {
        Book addedBook = libraryService.addBook(nameBook, nameAuthor);
        assertNotNull(addedBook);
        assertEquals(nameBook, addedBook.getNameBook());
        assertEquals(nameAuthor, addedBook.getNameAuthor());

        // Проверка, что книга добавлена в общий список
        MyArrayList<Book> allBooks = (MyArrayList<Book>) libraryService.getAllBooks();
        assertTrue(allBooks.contains(addedBook));
    }

    @Test
    public void testSearchBookByName() {
        // Проверка поиска книги по названию
        MyArrayList<Book> foundBooks = (MyArrayList<Book>) libraryService.searchBookByName("Sherlock Holmes");
        assertFalse(foundBooks.isEmpty());
        assertEquals("Sherlock Holmes", foundBooks.get(0).getNameBook());
    }

    @Test
    public void testSearchBookByAuthor() {
        // Проверка поиска книги по автору
        MyArrayList<Book> foundBooks = (MyArrayList<Book>) libraryService.searchBookByAuthor("Arthur Conan Doyle");
        assertFalse(foundBooks.isEmpty());
        assertEquals("Arthur Conan Doyle", foundBooks.get(0).getNameAuthor());
    }

    @Test
    public void testReturnBook() {
        // Проверка возврата книги
        int bookId = 1;
        libraryService.returnBook(bookId);
    }

    @Test
    // Проверка займа книги
    public void testBorrowBook() {
        int bookId = 1;
        libraryService.borrowBook(bookId);
    }

    @Test
    public void testRegisterNewReader() {
        ReaderService readerService = new ReaderService(new ReaderRepository(new MyArrayList<>()));
        Reader newReader = readerService.registerNewReader("newuser@gmail.com", "StrongPassword1!");
        assertNotNull(newReader);
        assertEquals("newuser@gmail.com", newReader.getEmail());
    }

    @Test
    public void testInvalidEmailRegistration() {
        ReaderService readerService = new ReaderService(new ReaderRepository(new MyArrayList<>()));
        Reader invalidReader = readerService.registerNewReader("invalidemail", "StrongPassword1!");
        assertNull(invalidReader);
    }


    @Test
    public void testExistingEmailRegistration() {
        MyArrayList<Reader> existingReaders = new MyArrayList<>();
        existingReaders.add(new Reader(1, "john@gmail.com", "Qwerty12$"));
        ReaderService readerService = new ReaderService(new ReaderRepository(existingReaders));
        Reader existingReader = readerService.registerNewReader("john@gmail.com", "NewPassword123!");
        assertNull(existingReader);
    }

    @AfterEach
    void tearDown() {
    }
}