package src;


import src.model.Book;
import src.repository.BookRepository;
import src.service.LibraryService;
import src.util.MyList;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Создаем экземпляр репозитория
        BookRepository bookRepository = new BookRepository();

        // Создаем экземпляр сервиса и передаем ему репозиторий
        LibraryService libraryService = new LibraryService(bookRepository);

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Library!");

        int choice;
        do {
            System.out.println("\nMenu:");
            System.out.println("1. Add a Book");
            System.out.println("2. Search Book by Name");
            System.out.println("3. Search Book by Author");
            System.out.println("4. Borrow a Book");
            System.out.println("5. Return a Book");
            System.out.println("6. View All Books");
            System.out.println("7. View All Free Books");
            System.out.println("8. View All Borrowed Books");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter author name: ");
                    String author = scanner.nextLine();
                    Book addedBook = libraryService.addBook(title, author);
                    if (addedBook != null) {
                        System.out.println("Book added successfully: " + addedBook);
                    } else {
                        System.out.println("Book already exists.");
                    }
                    break;
                case 2:
                    System.out.print("Enter book name to search: ");
                    String searchTitle = scanner.nextLine();
                    MyList<Book> booksByName = libraryService.searchBookByName(searchTitle);
                    displayBooks(booksByName);
                    break;
                case 3:
                    System.out.print("Enter author name to search: ");
                    String searchAuthor = scanner.nextLine();
                    MyList<Book> booksByAuthor = libraryService.searchBookByAuthor(searchAuthor);
                    displayBooks(booksByAuthor);
                    break;
                case 4:
                    MyList<Book> getFreeBooks = libraryService.getAllFreeBooks();
                    System.out.println("All Free Books");
                    displayBooks(getFreeBooks);
                    System.out.print("Enter book ID to borrow: ");
                    int borrowId = scanner.nextInt();
                    libraryService.borrowBook(borrowId);
                    break;
                case 5:
                    System.out.print("Enter book ID to return: ");
                    int returnId = scanner.nextInt();
                    libraryService.returnBook(returnId);
                    break;
                case 6:
                    MyList<Book> allBooks = libraryService.getAllBooks();
                    System.out.println("All Books:");
                    displayBooks(allBooks);
                    break;
                case 7:
                    MyList<Book> freeBooks = libraryService.getAllFreeBooks();
                    System.out.println("All Free Books");
                    displayBooks(freeBooks);
                    break;
                case 8:
                    MyList<Book> borrowedBooks = libraryService.getAllBorrowedBooks();
                    System.out.println("All Borrowed Books");
                    displayBooks(borrowedBooks);
                case 9:
                    System.out.println("Exiting the Library. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 9.");
            }
        } while (choice != 9);

        scanner.close();
    }

    private static void displayBooks(MyList<Book> books) {
        if (books.isEmpty()) {
            System.out.println("No books found.");
        } else {
            System.out.println("Books:");
            for (int i = 0; i < books.size(); i++) {
                System.out.println(books.get(i));
            }
        }
    }
}