package com.bookinventoryapp.service;

import com.bookinventoryapp.model.Book;
import com.bookinventoryapp.model.BookDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UpdateCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        double price = Double.parseDouble(request.getParameter("price"));

        // Create a new Book object
        Book book = new Book();
        book.setId(id);
        book.setTitle(title);
        book.setAuthor(author);
        book.setPrice(price);

        // Save the book to the database
        BookDao.updateBook(book);
        System.out.println("book updated");

        response.sendRedirect(request.getContextPath() + "/books");

    }
}
