package src.model;

public class Book {
    private int bookId;
    private String nameBook;
    private String nameAuthor;
    private boolean available;

    public Book(int bookId, String nameBook, String nameAuthor) {
        this.bookId = bookId;
        this.nameBook = nameBook;
        this.nameAuthor = nameAuthor;
        this.available = true;
    }

    public int getBookId() {
        return bookId;
    }

    public String getNameBook() {
        return nameBook;
    }

    public String getNameAuthor() {
        return nameAuthor;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "#" + bookId +
                " - " + nameBook + '\'' +
                " (" + nameAuthor + '\'' +
                ')';
    }
}
