package com.bookinventoryapp.service;

import com.bookinventoryapp.model.Book;
import com.bookinventoryapp.model.BookDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CreateCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        double price = Double.parseDouble(request.getParameter("price"));

        // Create a new Book object
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setPrice(price);

        // Save the book to the database
        BookDao.createBook(book);
        System.out.println("book created");

//        List<Book> allBooks = BookDao.getAllBooks();

//        request.setAttribute("books", allBooks);

        // Redirect back to the book list
        // After adding the book to the database
        response.sendRedirect(request.getContextPath() + "/books");
//        response.sendRedirect("list.jsp");
//        request.getRequestDispatcher("list.jsp").forward(request, response);
    }
}
