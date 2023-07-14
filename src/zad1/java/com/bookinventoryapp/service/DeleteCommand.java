package com.bookinventoryapp.service;

import com.bookinventoryapp.model.Book;
import com.bookinventoryapp.model.BookDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        BookDao.deleteBook(id);

        response.sendRedirect(request.getContextPath() + "/books");
    }
}
