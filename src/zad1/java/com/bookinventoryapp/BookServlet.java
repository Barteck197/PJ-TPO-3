package com.bookinventoryapp;

import com.bookinventoryapp.model.Book;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "books", value = "/books")
public class BookServlet extends HttpServlet {
    DataSource dataSource;

    @Override
    public void init() throws ServletException {
        try {
            Context init = new InitialContext();
            Context contx = (Context) init.lookup("java:/comp/env");
            dataSource = (DataSource) contx.lookup("jdbc/LibraryDB");

        } catch (NamingException exc) {
            throw new ServletException(
                    "Nie mogę uzyskać źródła java:comp/env/jdbc/LibraryDB", exc);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Book> books = new ArrayList<>();

        Connection connection = null;
        try {
            synchronized (dataSource) {
                connection = dataSource.getConnection();
            }
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM BOOKS");
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setPrice(resultSet.getDouble("price"));
                books.add(book);
            }
        } catch (SQLException exc) {
            exc.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        request.setAttribute("books", books);
        request.getRequestDispatcher("list.jsp").forward(request, response);
    }
}
