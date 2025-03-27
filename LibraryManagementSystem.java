import java.util.*;

public class LibraryManagementSystem {
    private static Library library = new Library();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        
        while (running) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add a Book");
            System.out.println("2. View All Books");
            System.out.println("3. Search Book by ID");
            System.out.println("4. Search Book by Title");
            System.out.println("5. Update Book Details");
            System.out.println("6. Delete a Book");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1-7.");
                continue;
            }
            
            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    viewAllBooks();
                    break;
                case 3:
                    searchBookById();
                    break;
                case 4:
                    searchBookByTitle();
                    break;
                case 5:
                    updateBook();
                    break;
                case 6:
                    deleteBook();
                    break;
                case 7:
                    running = false;
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        
        scanner.close();
    }

    private static void addBook() {
        System.out.println("\nAdd a New Book");
        
        String id = getInput("Enter Book ID: ", true);
        String title = getInput("Enter Title: ", false);
        String author = getInput("Enter Author: ", false);
        String genre = getInput("Enter Genre: ", false);
        
        String status;
        do {
            status = getInput("Enter Availability Status (Available/Checked Out): ", false);
            if (!status.equalsIgnoreCase("Available") && !status.equalsIgnoreCase("Checked Out")) {
                System.out.println("Invalid status. Please enter either 'Available' or 'Checked Out'.");
            }
        } while (!status.equalsIgnoreCase("Available") && !status.equalsIgnoreCase("Checked Out"));
        
        Book newBook = new Book(id, title, author, genre, status);
        if (library.addBook(newBook)) {
            System.out.println("Book added successfully!");
        } else {
            System.out.println("Error: A book with this ID already exists.");
        }
    }

    private static void viewAllBooks() {
        System.out.println("\nAll Books in Library");
        List<Book> books = library.getAllBooks();
        if (books.isEmpty()) {
            System.out.println("No books available in the library.");
        } else {
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    private static void searchBookById() {
        System.out.println("\nSearch Book by ID");
        String id = getInput("Enter Book ID: ", true);
        Book book = library.searchBookById(id);
        if (book != null) {
            System.out.println("Book found:");
            System.out.println(book);
        } else {
            System.out.println("No book found with ID: " + id);
        }
    }

    private static void searchBookByTitle() {
        System.out.println("\nSearch Book by Title");
        String title = getInput("Enter Book Title: ", false);
        List<Book> books = library.searchBookByTitle(title);
        if (books.isEmpty()) {
            System.out.println("No books found with title: " + title);
        } else {
            System.out.println("Found " + books.size() + " book(s):");
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    private static void updateBook() {
        System.out.println("\nUpdate Book Details");
        String id = getInput("Enter Book ID to update: ", true);
        Book book = library.searchBookById(id);
        
        if (book == null) {
            System.out.println("No book found with ID: " + id);
            return;
        }
        
        System.out.println("Current details:");
        System.out.println(book);
        
        String title = getInput("Enter new Title (leave blank to keep current): ", true);
        String author = getInput("Enter new Author (leave blank to keep current): ", true);
        String genre = getInput("Enter new Genre (leave blank to keep current): ", true);
        String status = getInput("Enter new Status (Available/Checked Out, leave blank to keep current): ", true);
        
        // Only update fields that have new values
        String newTitle = title.isEmpty() ? book.getTitle() : title;
        String newAuthor = author.isEmpty() ? book.getAuthor() : author;
        String newGenre = genre.isEmpty() ? book.getGenre() : genre;
        String newStatus = status.isEmpty() ? book.getAvailabilityStatus() : status;
        
        if (library.updateBook(id, newTitle, newAuthor, newGenre, newStatus)) {
            System.out.println("Book updated successfully!");
        } else {
            System.out.println("Error updating book.");
        }
    }

    private static void deleteBook() {
        System.out.println("\nDelete a Book");
        String id = getInput("Enter Book ID to delete: ", true);
        if (library.deleteBook(id)) {
            System.out.println("Book deleted successfully!");
        } else {
            System.out.println("No book found with ID: " + id);
        }
    }

    private static String getInput(String prompt, boolean allowEmpty) {
        String input;
        do {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (!allowEmpty && input.isEmpty()) {
                System.out.println("This field cannot be empty. Please try again.");
            }
        } while (!allowEmpty && input.isEmpty());
        return input;
    }
} 
// echo "# LibraryManagement" >> README.md
// git init
// git add README.md
// git commit -m "first commit"
// git branch -M main
// git remote add origin https://github.com/Rohanchaursiya/LibraryManagement.git
// git push -u origin main