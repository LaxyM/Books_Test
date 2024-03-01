package src.service;

import src.model.Book;
import src.util.MyArrayList;
import src.util.MyList;

public class GetAllAvailableBooks {
    // Статический метод для получения списка всех свободных книг
    public static MyList<Book> getAllAvailableBooks(MyList<Book> books) {
        MyList<Book> availableBooks = new MyArrayList<>(); // Используем интерфейс MyList<Book>
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            if (book.isAvailable()) {
                availableBooks.add(book);
            }
        }
        return availableBooks;
    }
    // Чтобы вызвать данный метод
    // MyList<Book> books = ...; // Получаем список книг
    // MyList<Book> availableBooks = GetAllAvailableBooks.getAllAvailableBooks(books);



}
