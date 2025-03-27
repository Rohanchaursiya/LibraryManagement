import java.util.*;
class Library {
    private List<Book> books;

    public Library() {
        this.books = new ArrayList<>();
    }

    // Add a new book
    public boolean addBook(Book book) {
        // Check if book ID already exists
        for (Book b : books) {
            if (b.getId().equals(book.getId())) {
                return false;
            }
        }
        books.add(book);
        return true;
    }

    // View all books
    public List<Book> getAllBooks() {
        return new ArrayList<>(books);
    }

    // Search book by ID
    public Book searchBookById(String id) {
        for (Book book : books) {
            if (book.getId().equals(id)) {
                return book;
            }
        }
        return null;
    }

    // Search book by title
    public List<Book> searchBookByTitle(String title) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                result.add(book);
            }
        }
        return result;
    }

    // Update book details
    public boolean updateBook(String id, String title, String author, String genre, String status) {
        Book book = searchBookById(id);
        if (book != null) {
            book.setTitle(title);
            book.setAuthor(author);
            book.setGenre(genre);
            book.setAvailabilityStatus(status);
            return true;
        }
        return false;
    }

    // Delete a book
    public boolean deleteBook(String id) {
        Book book = searchBookById(id);
        if (book != null) {
            books.remove(book);
            return true;
        }
        return false;
    }
}