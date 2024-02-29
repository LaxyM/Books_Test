package src.service;


import src.model.Book;
import src.repository.BookRepository;
import src.util.MyList;

public class LibraryService {
    private final BookRepository bookRepository;

    public LibraryService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Выдает список всех книг
    public MyList<Book> getAllBooks() {
       return bookRepository.getAllBooks();
    }


    // Проверяет если книга, если нет - то добавляет
    public Book addBook(String nameBook, String nameAuthor) {
        boolean isExist = bookRepository.isBookExist(nameBook, nameAuthor);
        if (isExist) {
            return null;
        }
        Book book = bookRepository.addBook(nameBook, nameAuthor);
        return book;
    }



    // Ищем книгу по названию (игнорируем регистр)
    public MyList<Book> searchBookByName(String nameBook) {
        return bookRepository.searchBookByName(nameBook);
    }


    // Ищем книгу по автору (игнорируем регистр)
    public MyList<Book> searchBookByAuthor(String nameAuthor) {
        return bookRepository.searchBookByAuthor(nameAuthor);
    }

    // Забираем книгу из библиотеки
    public void borrowBook(int bookId) {
        Book book = bookRepository.findBookId(bookId);
        if (book != null && book.isAvailable()) {
            bookRepository.markBookUnAvailable(bookId);
            System.out.println("Book has been borrowed: " + book);
        } else {
            System.out.println("Book is not available for borrowing.");
        }
    }

    // Возвращаем книгу в библиотеку
    public void returnBook(int bookId) {
        Book book = bookRepository.findBookId(bookId);
        if (book != null && !book.isAvailable()) {
            bookRepository.markBookAvailable(bookId);
            System.out.println("Book has been returned: " + book);
        } else {
            System.out.println("Book cannot be returned.");
        }
    }



}// end