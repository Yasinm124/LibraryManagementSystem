public class Book {
    private int id;
    private String title;
    private String author;
    private boolean issued;
    private int issuedTo; // user id if issued, otherwise -1

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.issued = false;
        this.issuedTo = -1;
    }

    // Getters and setters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isIssued() { return issued; }
    public int getIssuedTo() { return issuedTo; }

    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setIssued(boolean issued) { this.issued = issued; }
    public void setIssuedTo(int userId) { this.issuedTo = userId; }

    @Override
    public String toString() {
        String status = issued ? "Issued to ID: " + issuedTo : "Available";
        return String.format("ID: %d | Title: %s | Author: %s | %s", id, title, author, status);
    }
}
