package com.grokhotov.homeLibrary;

import java.io.IOException;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        BookDao bookDao = new BookDao();
        ReaderDao readerDao = new ReaderDao();
        System.out.println(bookDao.getBook(2));

    }
}
