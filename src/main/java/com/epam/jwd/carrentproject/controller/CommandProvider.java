package com.epam.jwd.carrentproject.controller;

import com.epam.jwd.carrentproject.controller.impl.ChangePasswordCommand;
import com.epam.jwd.carrentproject.controller.impl.DefaultCommand;
import com.epam.jwd.carrentproject.controller.impl.admin.FindAllUsersCommand;
import com.epam.jwd.carrentproject.controller.impl.LoginCommand;
import com.epam.jwd.carrentproject.controller.impl.LogoutCommand;
import com.epam.jwd.carrentproject.controller.impl.AddUserCommand;
import com.epam.jwd.carrentproject.controller.impl.RegistrationCommand;
import com.epam.jwd.carrentproject.controller.impl.UpdateUserPersonalInfoCommand;
import com.epam.jwd.carrentproject.controller.impl.admin.InactivateUserCommand;
import com.epam.jwd.carrentproject.controller.impl.to.*;
import com.epam.jwd.carrentproject.controller.impl.to.GoToChangePasswordForm;
import com.epam.jwd.carrentproject.controller.impl.to.GoToChangePersonalInfoForm;
import com.epam.jwd.carrentproject.controller.impl.to.GoToHomePageCommand;
import com.epam.jwd.carrentproject.controller.impl.to.GoToLoginPageCommand;
import com.epam.jwd.carrentproject.controller.impl.to.GoToMainPageCommand;
import com.epam.jwd.carrentproject.controller.impl.to.GoToRegistrationPageCommand;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider{
    private static Map<String, Command> commands;

    static {
        //продолжать наполнять командами
        commands= new HashMap<>();
        commands.put("adduser", new AddUserCommand());
        commands.put("login", new LoginCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("default", new DefaultCommand());
        commands.put("findallusers", new FindAllUsersCommand());
        commands.put("registrationuser", new RegistrationCommand());
        commands.put("gotomainpage", new GoToMainPageCommand());
        commands.put("gotoregistrationpage", new GoToRegistrationPageCommand());
        commands.put("updateuserpersonalinfo", new UpdateUserPersonalInfoCommand());
        commands.put("gotologinpage", new GoToLoginPageCommand());
        commands.put("gotoaccountpage", new GoToAccountPageCommand());
        commands.put("gotohomepage", new GoToHomePageCommand());
        commands.put("gotochangepersonalinfo", new GoToChangePersonalInfoForm());
        commands.put("gotochangepassword", new GoToChangePasswordForm());
        commands.put("changepassword", new ChangePasswordCommand());
        commands.put("gotoinactivateuserpage", new GoToInactivateUserPage());
        commands.put("inactivateuser", new InactivateUserCommand());

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
