package src.view;


import src.model.Book;
import src.model.Reader;
import src.repository.ReaderRepository;
import src.service.LibraryService;
import src.service.ReaderService;
import src.util.MyList;

import java.util.Scanner;

public class ConsoleMenu {
    private final LibraryService libraryService;
    private final ReaderService service;
    private final ReaderRepository repository;
    private final Scanner scanner = new Scanner(System.in);

    public ConsoleMenu(LibraryService libraryService, ReaderService service, ReaderRepository repository) {
        this.libraryService = libraryService;
        this.service = service;
        this.repository = repository;
    }

    public void run() {
        showMenu();
    }


    private void showMenu() {
        while (true) {
            System.out.println("\nWelcome to the Library!");
            System.out.println("1 -> User menu");
            System.out.println("2 -> Library menu");
            System.out.println("0 -> Exit");
            System.out.println("\nEnter your choice:");
            int input = scanner.nextInt();
            scanner.nextLine();

            //проверка не выбран ли пункт выход
            if (input == 0) {
                System.out.println("Bye bye");
                System.exit(0); // завершение работа приложения
                // break;
            }

            if (input == 1) {
                showUserMenu();
//                System.out.println("Sorry, we have a technical problem. Try again in a couple of years :)");
//                waitRead();
//                showMenu();
            }
            if (input == 2) {
                showLibraryMenu();
            }
            System.out.println("\nInvalid choice. Try again!\n");

        }
    }

    private void showUserMenu() {
        while (true) {
            System.out.println("\nWelcome to the User Menu");
            System.out.println("1 -> Register");
            System.out.println("2 -> Authorise");
            System.out.println("3 -> Logout");
            System.out.println("0 -> Return to previous menu");
            System.out.println("\nEnter your choice:");
            int input = scanner.nextInt();
            scanner.nextLine();

            switch (input) {
                case 1:
                    String email;
                    String password;
                    boolean isEmailValid = false;
                    boolean isPasswordValid = false;
                    boolean isEmailExist = false;

                    do {
                        System.out.println("Enter your e-mail.");
                        email = scanner.nextLine();

                        if (email == null || email.isEmpty()) {
                            System.out.println("E-mail field must be filled out");
                            continue;
                        }

                        isEmailValid = service.isEmailValid(email);
                        if (!isEmailValid) {
                            System.out.println("Invalid e-mail address. Please try again.");
                            continue;
                        }

                        isEmailExist = repository.isReaderEmailExist(email);
                        if (isEmailExist) {
                            System.out.println("E-mail already exists. Please try again.");
                            continue;
                        }
                    } while (!isEmailValid || isEmailExist);

                    do {
                        System.out.println("Enter your password.");
                        password = scanner.nextLine();

                        if (password == null || password.isEmpty()) {
                            System.out.println("Password field must be filled out");
                            continue;
                        }

                        isPasswordValid = service.isPasswordValid(password);
                        if (!isPasswordValid) {
                            System.out.println("Invalid password. Please try again.");
                            continue;
                        }

                    } while (!isPasswordValid);

                    Reader registerReader = service.registerNewReader(email, password);
                    System.out.println("Registration completed successfully.");
                    break;

                case 2:
                    String email1;
                    String password1;
                    isEmailValid = false;
                    isPasswordValid = false;
                    isEmailExist = false;
                    boolean isPasswordExist = false;

                    do {
                        System.out.println("Enter your e-mail.");
                        email1 = scanner.nextLine();

                        if (email1 == null || email1.isEmpty()) {
                            System.out.println("E-mail field must be filled out");
                            continue;
                        }

                        isEmailValid = service.isEmailValid(email1);
                        if (!isEmailValid) {
                            System.out.println("Invalid e-mail address. Please try again.");
                            continue;
                        }

                        isEmailExist = repository.isReaderEmailExist(email1);
                        if (!isEmailExist) {
                            System.out.println("E-mail not found. Please try again.");
                            continue;
                        }
                    } while (!isEmailValid || !isEmailExist);

                    do {
                        System.out.println("Enter your password.");
                        password1 = scanner.nextLine();

                        if (password1 == null || password1.isEmpty()) {
                            System.out.println("Password field must be filled out");
                            continue;
                        }

                        isPasswordValid = service.isPasswordValid(password1);
                        if (!isPasswordValid) {
                            System.out.println("Invalid password. Please try again.");
                            continue;
                        }

                        isPasswordExist = repository.isReaderPasswordExist(password1);
                        if (!isPasswordExist) {
                            System.out.println("Password not found. Please try again.");
                            continue;
                        }

                    } while (!isPasswordValid || !isPasswordExist);

                    Reader authoriseReader = service.authoriseReader(email1, password1);
                    System.out.println("Authorisation completed successfully.");
                    break;

                case 3:
                    Reader activeReader = service.getActiveReader();
                    if (activeReader != null) activeReader = null;
                    System.out.println("Logout completed");
                    waitRead();
                    break;

                case 0:
                    showMenu();
                    break;

                default:
                    System.out.println("Invalid choice. Please try again");
                    break;
            }
        }
    }


    private void showLibraryMenu () {
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
            System.out.println("\n0. Return to previous menu");
            System.out.print("\nEnter your choice: ");
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
                    waitRead();
                    break;
                case 2:
                    System.out.print("Enter book name to search: ");
                    String searchTitle = scanner.nextLine();
                    MyList<Book> booksByName = libraryService.searchBookByName(searchTitle);
                    displayBooks(booksByName);
                    waitRead();
                    break;
                case 3:
                    System.out.print("Enter author name to search: ");
                    String searchAuthor = scanner.nextLine();
                    MyList<Book> booksByAuthor = libraryService.searchBookByAuthor(searchAuthor);
                    displayBooks(booksByAuthor);
                    waitRead();
                    break;
                case 4:
                    MyList<Book> getFreeBooks = libraryService.getAllFreeBooks();
                    System.out.println("All Free Books:");
                    displayBooks(getFreeBooks);
                    System.out.print("Enter book ID to borrow: ");
                    int borrowId = scanner.nextInt();
                    libraryService.borrowBook(borrowId);
                    waitRead();
                    break;
                case 5:
                    System.out.print("Enter book ID to return: ");
                    int returnId = scanner.nextInt();
                    libraryService.returnBook(returnId);
                    waitRead();
                    break;
                case 6:
                    MyList<Book> allBooks = libraryService.getAllBooks();
                    System.out.println("All Books:");
                    displayBooks(allBooks);
                    waitRead();
                    break;
                case 7:
                    MyList<Book> freeBooks = libraryService.getAllFreeBooks();
                    System.out.println("All Free Books:");
                    displayBooks(freeBooks);
                    waitRead();
                    break;
                case 8:
                    MyList<Book> borrowedBooks = libraryService.getAllBorrowedBooks();
                    System.out.println("All Borrowed Books:");
                    displayBooks(borrowedBooks);
                    waitRead();
                case 0:
                    showMenu();
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 0 and 8.");
            }
        } while (choice != 8);
    }

    private void waitRead () {
        System.out.println("\nPress Enter to continue");
        scanner.nextLine();
    }

    private static void displayBooks (MyList < Book > books) {
        if (books.isEmpty()) {
            System.out.println("No books found.");
        } else {
            //System.out.println("Books:");
            for (int i = 0; i < books.size(); i++) {
                System.out.println(books.get(i));
            }
        }
    }




}






