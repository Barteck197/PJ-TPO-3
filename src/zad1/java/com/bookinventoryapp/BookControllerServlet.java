package com.bookinventoryapp;

import com.bookinventoryapp.service.Command;
import com.bookinventoryapp.service.CommandFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "BookControllerServlet", value = "/BookControllerServlet")
public class BookControllerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String commandName = request.getParameter("command");       // insert/update/delete

        // Create the appropriate command based on the command name
        CommandFactory commandFactory = new CommandFactory();
        Command command = commandFactory.createCommand(commandName);

        // Execute the command
        command.execute(request, response);
    }
}
