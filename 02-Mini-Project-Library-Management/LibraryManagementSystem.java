import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

interface Borrowable {
    void borrow() throws LibraryException;
    void returnItem();
}

abstract class LibraryItem {
    private final int id;
    private final String title;
    private boolean available = true;

    LibraryItem(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public boolean isAvailable() { return available; }
    protected void setAvailable(boolean value) { available = value; }

    public abstract void displayDetails();
}

class Book extends LibraryItem implements Borrowable {
    private final String author;

    Book(int id, String title, String author) {
        super(id, title);
        this.author = author;
    }

    @Override
    public void borrow() throws LibraryException {
        if (!isAvailable()) throw new LibraryException("Book is already borrowed.");
        setAvailable(false);
    }

    @Override
    public void returnItem() { setAvailable(true); }

    @Override
    public void displayDetails() {
        System.out.println(getId() + " | " + getTitle() + " | " + author
                + " | " + (isAvailable() ? "Available" : "Borrowed"));
    }
}

class LibraryException extends Exception {
    LibraryException(String message) { super(message); }
}

class Library {
    private final List<Book> books = new ArrayList<>();

    public void addBook(Book book) { books.add(book); }

    public void showBooks() {
        if (books.isEmpty()) {
            System.out.println("No books are currently listed.");
            return;
        }
        System.out.println("ID | Title | Author | Status");
        for (Book book : books) book.displayDetails();
    }

    private Book findBook(int id) throws LibraryException {
        for (Book book : books) {
            if (book.getId() == id) return book;
        }
        throw new LibraryException("No book found with ID " + id);
    }

    public void borrowBook(int id) throws LibraryException {
        findBook(id).borrow();
        System.out.println("Book borrowed successfully.");
    }

    public void returnBook(int id) throws LibraryException {
        Book book = findBook(id);
        if (book.isAvailable()) throw new LibraryException("This book was not borrowed.");
        book.returnItem();
        System.out.println("Book returned successfully.");
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();
        library.addBook(new Book(1, "Clean Code", "Robert C. Martin"));
        library.addBook(new Book(2, "Effective Java", "Joshua Bloch"));
        library.addBook(new Book(3, "The Java Programming Language", "James Gosling"));

        // A separate thread demonstrates a simple background task.
        Thread welcomeThread = new Thread(() ->
                System.out.println("Welcome to the Library Management System!"));
        welcomeThread.start();

        try (Scanner scanner = new Scanner(System.in)) {
            boolean running = true;
            while (running) {
                System.out.println("\\n1. View books  2. Borrow book  3. Return book  4. Exit");
                System.out.print("Choose an option: ");
                String choice = scanner.nextLine().trim();

                try {
                    switch (choice) {
                        case "1":
                            library.showBooks();
                            break;
                        case "2":
                            System.out.print("Enter book ID: ");
                            library.borrowBook(Integer.parseInt(scanner.nextLine().trim()));
                            break;
                        case "3":
                            System.out.print("Enter book ID: ");
                            library.returnBook(Integer.parseInt(scanner.nextLine().trim()));
                            break;
                        case "4":
                            running = false;
                            System.out.println("Thank you for using the system.");
                            break;
                        default:
                            System.out.println("Please choose 1, 2, 3, or 4.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid numeric ID.");
                } catch (LibraryException e) {
                    System.out.println("Operation failed: " + e.getMessage());
                }
            }
        }
    }
}
