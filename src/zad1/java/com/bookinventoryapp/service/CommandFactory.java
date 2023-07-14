package com.bookinventoryapp.service;


public class CommandFactory {
    public Command createCommand(String commandName) {
        if (commandName == null) {
            return null;
        }

        if (commandName.equalsIgnoreCase("create")) {
            return new CreateCommand();
        } else if (commandName.equalsIgnoreCase("read")) {
            return new ReadCommand();
        } else if (commandName.equalsIgnoreCase("update")) {
            return new UpdateCommand();
        } else if (commandName.equalsIgnoreCase("delete")) {
            return new DeleteCommand();
        }

        return null;
    }
}

