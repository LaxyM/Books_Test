package src.test;

import org.junit.jupiter.api.Test;
import src.model.Book;
import src.service.GetAllAvailableBooks;
import src.util.MyArrayList;
import src.util.MyList;

import static org.junit.jupiter.api.Assertions.*;

public class GetAllAvailableBooksTest {
    // Проверяем метод для получения списка всех свободных книг
    @Test
    void testGetAllAvailableBooks() {
        // Создаем список книг
        MyList<Book> books = new MyArrayList<>();
        Book book1 = new Book(1, "Book 1", "Author 1");
        book1.setAvailable(true);
        Book book2 = new Book(2, "Book 2", "Author 2");
        book2.setAvailable(false);
        Book book3 = new Book(3, "Book 3", "Author 3");
        book3.setAvailable(true);
        books.addAll(book1, book2, book3);

        // Вызываем метод, который мы хотим протестировать
        MyList<Book> availableBooks = GetAllAvailableBooks.getAllAvailableBooks(books);

        // Проверяем, что только доступные книги были добавлены в список доступных книг
        assertEquals(2, availableBooks.size()); // Ожидаемый результат: 2
        assertEquals(book1, availableBooks.get(0)); // Проверяем, что книга book1 есть в списке доступных книг
        assertEquals(book3, availableBooks.get(1)); // Проверяем, что книга book3 есть в списке доступных книг
    }

    // Тест на пустой список книг
    @Test
    void testEmptyBookList() {
        // Создаем пустой список книг
        MyList<Book> emptyBookList = new MyArrayList<>();

        // Получаем список доступных книг
        MyList<Book> availableBooks = GetAllAvailableBooks.getAllAvailableBooks(emptyBookList);

        // Проверяем, что список доступных книг также пустой
        assertTrue(availableBooks.isEmpty());
    }

    // Тест на отсутствие доступных книг
    @Test
    void testNoAvailableBooks() {
        // Создаем список книг, где все книги недоступны
        MyList<Book> allBooks = new MyArrayList<>();
        allBooks.add(new Book(1, "Book1", "Author1"));
        allBooks.add(new Book(2, "Book2", "Author2"));
        allBooks.add(new Book(3, "Book3", "Author3"));

        // Получаем список доступных книг
        MyList<Book> availableBooks = GetAllAvailableBooks.getAllAvailableBooks(allBooks);

        // Проверяем, что список доступных книг пустой
        assertTrue(availableBooks.isEmpty());
    }

    // Тест на изменение статуса книг
    @Test
    void testSetAvailable() {
        // Создаем книгу и устанавливаем ее статус доступности в начальное значение false
        Book book = new Book(1, "Book1", "Author1");
        book.setAvailable(false);

        // Проверяем, что статус доступности книги изначально установлен как false
        assertFalse(book.isAvailable());

        // Меняем статус доступности книги на true
        book.setAvailable(true);

        // Проверяем, что статус доступности книги изменился на true
        assertTrue(book.isAvailable());
    }

}
