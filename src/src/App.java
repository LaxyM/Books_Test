package src;

import src.repository.BookRepository;
import src.repository.ReaderRepository;
import src.service.LibraryService;
import src.service.ReaderService;
import src.view.ConsoleMenu;

public class App {
    public static void main(String[] args) {
        BookRepository bookRepository = new BookRepository();
        LibraryService libraryService = new LibraryService(bookRepository);
        ReaderRepository readerRepository = new ReaderRepository();
        ReaderService readerService = new ReaderService(readerRepository);

        ConsoleMenu consoleMenu = new ConsoleMenu(libraryService,readerService,readerRepository);

        consoleMenu.run();



    }
}