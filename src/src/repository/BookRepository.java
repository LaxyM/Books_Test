package src.repository;

import src.model.Book;
import src.util.MyArrayList;
import src.util.MyList;

import java.util.concurrent.atomic.AtomicInteger;

public class BookRepository {
    private final MyList<Book> books;
    private final AtomicInteger currentId = new AtomicInteger(1);


    public BookRepository() {
        this.books = new MyArrayList<>();
        initBooks();
    }

    private void initBooks() {
        Book book = new Book(currentId.getAndIncrement(), "Sherlock Holmes", "Arthur Conan Doyle");
        Book book1 = new Book(currentId.getAndIncrement(), "To Kill a Mockingbird", "Harper Lee");
        Book book2 = new Book(currentId.getAndIncrement(), "The Great Gatsby", "F. Scott Fitzgerald");
        Book book3 = new Book(currentId.getAndIncrement(), "1918", "George Orwell");
        Book book4 = new Book(currentId.getAndIncrement(), "The Catcher in the Rye", " J.D. Salinger");
        books.addAll(book, book1, book2, book3, book4);
    }

    // Выдает список всех книг
    public MyList<Book> getAllBooks() {
        return books;
    }

    // Поиск книги по индексу
    public Book findBookId(int bookId) {
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            if (book.getBookId() == bookId) {
                return book;
            }
        }
        return null;
    }


    // Проверяет есть ли книга (по индексу, по названию, по автору)
    public boolean isBookExist(String nameBook, String nameAuthor) {
        if (nameBook == null || nameAuthor == null)
            return false;

        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getNameBook().equals(nameBook) && books.get(i).getNameAuthor().equals(nameAuthor))
                return true;
        }
        return false;
    }

    // Добавляет книгу
    public Book addBook(String nameBook, String nameAuthor) {
        if (nameBook == null || nameAuthor == null)
            return null;

        Book book = new Book(currentId.getAndIncrement(), nameBook, nameAuthor);
        books.add(book);
        return book;
    }


    // Ищем книгу по названию (игнорируем регистр)
    public MyList<Book> searchBookByName(String nameBook) {
        MyArrayList<Book> result = new MyArrayList<>();
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            if (book.getNameBook().toLowerCase().contains(nameBook.toLowerCase())) {
                result.add(book);
            }
        }

        if (result.isEmpty()) {
            System.out.println("Books with title '" + nameBook + "' not found.");
        }
        return result;
    }


    // Ищем книгу по автору (игнорируем регистр)
    public MyList<Book> searchBookByAuthor(String nameAuthor) {
        MyArrayList<Book> result = new MyArrayList<>();
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            if (book.getNameAuthor().toLowerCase().contains(nameAuthor.toLowerCase())) {
                result.add(book);
            }
        }

        if (result.isEmpty()) {
            System.out.println("Author's books '" + nameAuthor + "' not found.");
        }
        return result;
    }


    // Показывает есть ли такая книга в наличии
    public void markBookUnAvailable(int bookId) {
        Book book = findBookId(bookId);
        if (book != null) {
            book.setAvailable(false);
        }
    }

    // Показывает что книгу забрали
    public void markBookAvailable(int bookId) {
        Book book = findBookId(bookId);
        if (book != null) {
            book.setAvailable(true);
        }
    }


    // Выводит список всех книг которые есть в наличии
    public MyList<Book> getAllFreeBooks() {
        MyList<Book> freeBooks = new MyArrayList<>();
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            if (book.isAvailable()) {
                freeBooks.add(book);
            }
        }
        return freeBooks;
    }

    // Выводит список всех книг которых нет в наличии
    public MyList<Book> getAllBorrowedBooks() {
        MyList<Book> borrowedBooks = new MyArrayList<>();
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            if (!book.isAvailable()) {
                borrowedBooks.add(book);
            }
        }
        return borrowedBooks;
    }


}// end