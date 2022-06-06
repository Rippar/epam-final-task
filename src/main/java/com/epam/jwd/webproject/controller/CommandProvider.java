package com.epam.jwd.webproject.controller;

import com.epam.jwd.webproject.controller.impl.DefaultCommand;
import com.epam.jwd.webproject.controller.impl.LoginCommand;
import com.epam.jwd.webproject.controller.impl.LogoutCommand;
import com.epam.jwd.webproject.controller.impl.AddUserCommand;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider{
    private static Map<String, Command> commands;

    static {
        //продлжать наполнять командами
        commands= new HashMap<>();
        commands.put("adduser", new AddUserCommand());
        commands.put("login", new LoginCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("default", new DefaultCommand());
    }

    public CommandProvider() {
    }

    public static Command getCommand(String commandName) {
        Command command = commands.get(commandName);
        if (command != null) {
            return command;
        } else {
            return commands.get("default");
        }
    }
}
