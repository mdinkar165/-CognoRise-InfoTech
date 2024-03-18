import java.util.ArrayList;
import java.util.Scanner;

class Book {
    private String title;
    private String author;
    private boolean checkedOut;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.checkedOut = false;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isCheckedOut() {
        return checkedOut;
    }

    public void setCheckedOut(boolean checkedOut) {
        this.checkedOut = checkedOut;
    }
}

class LibraryCatalog {
    private ArrayList<Book> books;

    public LibraryCatalog() {
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public Book searchByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    public ArrayList<Book> searchByAuthor(String author) {
        ArrayList<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                result.add(book);
            }
        }
        return result;
    }

    public void checkOutBook(Book book) {
        if (book.isCheckedOut()) {
            System.out.println("Book is already checked out.");
        } else {
            book.setCheckedOut(true);
            System.out.println("Book checked out successfully.");
        }
    }

    public void returnBook(Book book) {
        if (book.isCheckedOut()) {
            book.setCheckedOut(false);
            System.out.println("Book returned successfully.");
        } else {
            System.out.println("Book is already available.");
        }
    }
}

class LibraryManagementSystem {
    public static void main(String[] args) {
        LibraryCatalog catalog = new LibraryCatalog();
        Scanner scanner = new Scanner(System.in);

        // Adding books to the library
        catalog.addBook(new Book("Java Programming", "John Doe"));
        catalog.addBook(new Book("Python for Beginners", "Jane Smith"));
        catalog.addBook(new Book("Data Structures and Algorithms", "Alice Johnson"));

        while (true) {
            System.out.println("\n1. Search by Title\n2. Search by Author\n3. Check out a book\n4. Return a book\n5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter the title to search: ");
                    String title = scanner.nextLine();
                    Book foundBookByTitle = catalog.searchByTitle(title);
                    if (foundBookByTitle != null) {
                        System.out.println("Book found: " + foundBookByTitle.getTitle() + " by " + foundBookByTitle.getAuthor());
                    } else {
                        System.out.println("Book not found.");
                    }
                    break;

                case 2:
                    System.out.print("Enter the author to search: ");
                    String author = scanner.nextLine();
                    ArrayList<Book> foundBooksByAuthor = catalog.searchByAuthor(author);
                    if (!foundBooksByAuthor.isEmpty()) {
                        System.out.println("Books found:");
                        for (Book b : foundBooksByAuthor) {
                            System.out.println(b.getTitle() + " by " + b.getAuthor());
                        }
                    } else {
                        System.out.println("No books found for the author.");
                    }
                    break;

                case 3:
                    System.out.print("Enter the title of the book to check out: ");
                    String checkOutTitle = scanner.nextLine();
                    Book checkOutBook = catalog.searchByTitle(checkOutTitle);
                    if (checkOutBook != null) {
                        catalog.checkOutBook(checkOutBook);
                    } else {
                        System.out.println("Book not found.");
                    }
                    break;

                case 4:
                    System.out.print("Enter the title of the book to return: ");
                    String returnTitle = scanner.nextLine();
                    Book returnBook = catalog.searchByTitle(returnTitle);
                    if (returnBook != null) {
                        catalog.returnBook(returnBook);
                    } else {
                        System.out.println("Book not found.");
                    }
                    break;

                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}
