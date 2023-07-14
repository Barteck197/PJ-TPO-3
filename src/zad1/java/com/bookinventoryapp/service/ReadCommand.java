package com.bookinventoryapp.service;

import com.bookinventoryapp.model.Book;
import com.bookinventoryapp.model.BookDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ReadCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Book> allBooks = BookDao.getAllBooks();

        request.setAttribute("books", allBooks);

        request.getRequestDispatcher("list.jsp").forward(request, response);
    }
}
