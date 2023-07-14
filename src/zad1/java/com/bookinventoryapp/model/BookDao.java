package com.bookinventoryapp.model;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDao {

    public static Connection getConnection() throws SQLException, ServletException {
        DataSource dataSource;
        try {
            Context init = new InitialContext();
            Context contx = (Context) init.lookup("java:/comp/env");
            dataSource = (DataSource) contx.lookup("jdbc/LibraryDB");

        } catch (NamingException exc) {
            throw new ServletException(
                    "Nie mogę uzyskać źródła java:comp/env/jdbc/LibraryDB", exc);
        }

        return dataSource.getConnection();
        /*Connection connection = null;
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/LibraryDB;username=admin;password=admin");
        } catch (Exception e) {
            System.out.println(e);
        }
        return connection;*/
    }

    public static int createBook(Book b) {
        int status = 0;
        try {
            Connection saveConnection = BookDao.getConnection();
            PreparedStatement ps = saveConnection.prepareStatement(
                    "insert into BOOKS(TITLE, AUTHOR, PRICE) values (?,?,?)");
            ps.setString(1, b.getTitle());
            ps.setString(2, b.getAuthor());
            ps.setDouble(3, b.getPrice());

            status = ps.executeUpdate();

            saveConnection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return status;
    }

    public static int updateBook(Book b) {
        int status = 0;
        try {
            Connection updateConnection = BookDao.getConnection();
            PreparedStatement ps = updateConnection.prepareStatement(
                    "update BOOKS set TITLE=?,AUTHOR=?,PRICE=? where id=?");
            ps.setString(1, b.getTitle());
            ps.setString(2, b.getAuthor());
            ps.setDouble(3, b.getPrice());
            ps.setInt(4, b.getId());

            status = ps.executeUpdate();

            updateConnection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return status;
    }

    public static int deleteBook(int id) {
        int status = 0;
        try {
            Connection deleteConnection = BookDao.getConnection();
            PreparedStatement ps = deleteConnection.prepareStatement("delete from BOOKS where id=?");
            ps.setInt(1, id);
            status = ps.executeUpdate();

            deleteConnection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    public static Book getBookByID(int id) {
        Book book = new Book();

        try {
            Connection getBookConnection = BookDao.getConnection();
            PreparedStatement ps = getBookConnection.prepareStatement("select * from BOOKS where id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                book.setId(rs.getInt(1));
                book.setTitle(rs.getString(2));
                book.setAuthor(rs.getString(3));
                book.setPrice(rs.getDouble(4));
            }
            getBookConnection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return book;
    }

    public static List<Book> getAllBooks() {
        List<Book> list = new ArrayList<>();

        try {
            Connection allBooksConnection = BookDao.getConnection();
            PreparedStatement ps = allBooksConnection.prepareStatement("select * from BOOKS");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt(1));
                book.setTitle(rs.getString(2));
                book.setAuthor(rs.getString(3));
                book.setPrice(rs.getDouble(4));
                list.add(book);
            }
            allBooksConnection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
