import java.util.ArrayList;

public class Library {
    private final ArrayList<Book> books = new ArrayList<>();
    private final ArrayList<User> users = new ArrayList<>();

    // Add book; return false if ID already exists
    public boolean addBook(Book book) {
        if (findBookIndexById(book.getId()) != -1) return false;
        books.add(book);
        return true;
    }

    // Add user; return false if ID already exists
    public boolean addUser(User user) {
        if (findUserIndexById(user.getId()) != -1) return false;
        users.add(user);
        return true;
    }

    // Get lists (for viewing)
    public ArrayList<Book> getBooks() { return books; }
    public ArrayList<User> getUsers() { return users; }

    // Issue a book to a user
    public boolean issueBook(int bookId, int userId) {
        int bi = findBookIndexById(bookId);
        int ui = findUserIndexById(userId);
        if (bi == -1 || ui == -1) return false;
        Book b = books.get(bi);
        if (b.isIssued()) return false;
        b.setIssued(true);
        b.setIssuedTo(userId);
        return true;
    }

    // Return a book
    public boolean returnBook(int bookId) {
        int bi = findBookIndexById(bookId);
        if (bi == -1) return false;
        Book b = books.get(bi);
        if (!b.isIssued()) return false;
        b.setIssued(false);
        b.setIssuedTo(-1);
        return true;
    }

    // Delete book by ID
    public boolean deleteBook(int bookId) {
        int bi = findBookIndexById(bookId);
        if (bi == -1) return false;
        books.remove(bi);
        return true;
    }

    // Delete user by ID (disallow if user has issued books)
    public boolean deleteUser(int userId) {
        int ui = findUserIndexById(userId);
        if (ui == -1) return false;
        // check for issued books
        for (Book b : books) {
            if (b.isIssued() && b.getIssuedTo() == userId) {
                return false; // can't delete user with issued book
            }
        }
        users.remove(ui);
        return true;
    }

    // Helper: find book index by ID
    private int findBookIndexById(int id) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId() == id) return i;
        }
        return -1;
    }

    // Helper: find user index by ID
    private int findUserIndexById(int id) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == id) return i;
        }
        return -1;
    }
}
 
