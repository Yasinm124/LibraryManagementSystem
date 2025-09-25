import java.util.Scanner;

public class LibraryManagementSystem {
    private static final Library library = new Library();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean flag = true;
        System.out.println("===== Library Management System =====");

        while (flag) {
            printMenu();
            int choice = readInt("Enter your choice: ");
            switch (choice) {
                case 1 -> addBook();
                case 2 -> viewBooks();
                case 3 -> addUser();
                case 4 -> viewUsers();
                case 5 -> issueBook();
                case 6 -> returnBook();
                case 7 -> deleteBook();
                case 8 -> deleteUser();
                case 9 -> {
                    System.out.println("Exiting...!");
                    flag = false;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
            System.out.println();
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("1. Add Book");
        System.out.println("2. View Books");
        System.out.println("3. Add User");
        System.out.println("4. View Users");
        System.out.println("5. Issue Book");
        System.out.println("6. Return Book");
        System.out.println("7. Delete Book");
        System.out.println("8. Delete User");
        System.out.println("9. Exit");
    }

    // Helpers to read input safely
    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try { return Integer.parseInt(line); }
            catch (NumberFormatException e) { System.out.println("Enter a valid integer."); }
        }
    }

    private static String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    // Menu actions
    private static void addBook() {
        System.out.println("--- Add Book ---");
        int id = readInt("Enter Book ID: ");
        String title = readString("Enter Title: ");
        String author = readString("Enter Author: ");
        boolean ok = library.addBook(new Book(id, title, author));
        System.out.println(ok ? "Book added." : "Book ID already exists.");
    }

    private static void viewBooks() {
        System.out.println("--- Books ---");
        if (library.getBooks().isEmpty()) {
            System.out.println("No books found.");
            return;
        }
        for (Book b : library.getBooks()) {
            System.out.println(b);
        }
    }

    private static void addUser() {
        System.out.println("--- Add User ---");
        int id = readInt("Enter User ID: ");
        String name = readString("Enter Name: ");
        boolean ok = library.addUser(new User(id, name));
        System.out.println(ok ? "User added." : "User ID already exists.");
    }

    private static void viewUsers() {
        System.out.println("--- Users ---");
        if (library.getUsers().isEmpty()) {
            System.out.println("No users found.");
            return;
        }
        for (User u : library.getUsers()) {
            System.out.println(u);
        }
    }

    private static void issueBook() {
        System.out.println("--- Issue Book ---");
        int bookId = readInt("Enter Book ID: ");
        int userId = readInt("Enter User ID: ");
        boolean ok = library.issueBook(bookId, userId);
        if (ok) System.out.println("Book issued.");
        else System.out.println("Issue failed (check IDs or book already issued).");
    }

    private static void returnBook() {
        System.out.println("--- Return Book ---");
        int bookId = readInt("Enter Book ID: ");
        boolean ok = library.returnBook(bookId);
        System.out.println(ok ? "Book returned." : "Return failed (book not found or not issued).");
    }

    private static void deleteBook() {
        System.out.println("--- Delete Book ---");
        int bookId = readInt("Enter Book ID: ");
        boolean ok = library.deleteBook(bookId);
        System.out.println(ok ? "Book deleted." : "Delete failed (book not found).");
    }

    private static void deleteUser() {
        System.out.println("--- Delete User ---");
        int userId = readInt("Enter User ID: ");
        boolean ok = library.deleteUser(userId);
        if (ok) System.out.println("User deleted.");
        else System.out.println("Delete failed (user not found or user has issued books).");
    }
}
