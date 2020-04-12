package com.grokhotov.homeLibrary;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class BookDao {
    private Connection connection;
    Scanner scanner = new Scanner(System.in);

//    todo: Implement connection pool

    public BookDao() throws IOException, SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Properties properties = new Properties();
        properties.load(new FileReader("dbConnection.properties"));
        Connection connection = DriverManager.getConnection(
                properties.getProperty("url"),
                properties.getProperty("user"),
                properties.getProperty("pass")
        );
        this.connection = connection;
    }

    public void createBook() throws SQLException {
        String authorsLastName = enterAuthorsLastName();
        String bookName = enterBookName();
        String isbn = enterIsbn();
        PreparedStatement ps = connection.prepareStatement("INSERT INTO home_library.book " +
                "(authors_last_name, book_title, isbn) VALUES (?,?,?)");
        ps.setString(1, authorsLastName);
        ps.setString(2, bookName);
        ps.setString(3, isbn);
        ps.execute();
        System.out.printf("Book %s by $s added\n", bookName, authorsLastName);
    }

    private String enterBookName() {
        System.out.println("Enter book name");
        return scanner.nextLine();
    }

    private String enterAuthorsLastName() {
        System.out.println("Enter author's last name");
        return scanner.nextLine();
    }

    private String enterIsbn() {
        System.out.println("Enter ISBN");
        return scanner.nextLine();
    }

    private int enterReader() {
        System.out.println("Enter reader's id: ");
        return scanner.nextInt();
    }

    public void deleteBook(int bookId) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("DELETE FROM home_library.book WHERE book_id=?");
        ps.setInt(1, bookId);
        ps.execute();
        System.out.printf("Book deleted\n"); // todo: Create method to get book name by its id
    }

    public Book getBook(int bookId) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement("SELECT authors_last_name, book_title, " +
                "isbn, reader_id, book_id FROM home_library.book WHERE book_id=?")) {
            ps.setInt(1, bookId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Book book = new Book(bookId);
                    book.setAutorsLastName(rs.getString(1));
                    book.setBookTitle(rs.getString(2));
                    book.setIsbn(rs.getString(3));
                    book.setBookId(rs.getInt(5));
                    return book;
                }
            }
        }
        return null;
    }

//    todo: finish getAllBooks()
    public Book getAllBooks() throws SQLException {
        Statement statement = connection.createStatement();
        String selectAll = "SELECT * FROM home_library.book";
        try {
            ResultSet rs = statement.executeQuery(selectAll);
            if (rs.next()) {
                do {
//                    Book book = new Book()
                } while (rs.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void giveBook(int bookId) throws SQLException {
        int readerId = enterReader();
        scanner.nextLine();
        PreparedStatement ps = connection.prepareStatement("UPDATE home_library.book " +
                "SET reader_id=? WHERE book_id=?");
        ps.setInt(1, readerId);
        ps.setInt(2, bookId);
        ps.execute();
        Book book = getBook(bookId);
        System.out.printf("Book %s is given to reader %d", book.getBookTitle(), readerId);
    }


}
