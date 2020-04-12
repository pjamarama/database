package com.grokhotov.homeLibrary;

public class Book {
    private String bookTitle;
    private String autorsLastName;
    private String isbn;
    private int readersId = 0;
    private int bookId;

    //    public Book(String bookTitle, String autorsLastName, String isbn, int readersId) {
    public Book(int bookId) {
        this.bookId = bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getAutorsLastName() {
        return autorsLastName;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getReadersId() {
        return readersId;
    }

    public void setReadersId(int readersId) {
        this.readersId = readersId;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public void setAutorsLastName(String autorsLastName) {
        this.autorsLastName = autorsLastName;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    @Override
    public String toString() {
        return String.format("Book ID:\t%d\nAuthor(s):\t%s\nTitle:\t%s\t",
                bookId, autorsLastName, bookTitle);
    }
}
