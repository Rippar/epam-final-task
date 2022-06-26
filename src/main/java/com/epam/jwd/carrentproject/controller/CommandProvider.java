package com.epam.jwd.carrentproject.controller;

import com.epam.jwd.carrentproject.controller.impl.*;
import com.epam.jwd.carrentproject.controller.impl.admin.*;
import com.epam.jwd.carrentproject.controller.impl.to.*;


import java.util.HashMap;
import java.util.Map;

import static com.epam.jwd.carrentproject.controller.constant.CommandName.*;

public class CommandProvider{
    private static Map<String, Command> commands;

    static {
        //продолжать наполнять командами
        commands= new HashMap<>();
        //commands.put(ADD_USER_COMMAND, new AddUserCommand());
        commands.put(LOGIN_COMMAND, new LoginCommand());
        commands.put(LOGOUT_COMMAND, new LogoutCommand());
        commands.put(DEFAULT_COMMAND, new DefaultCommand());
        commands.put(FIND_ALL_USERS_COMMAND, new FindAllUsersCommand());
        commands.put(REGISTRATION_USER_COMMAND, new RegistrationCommand());
        commands.put(GO_TO_MAIN_PAGE_COMMAND, new GoToMainPageCommand());
        commands.put(GO_TO_REGISTRATION_PAGE_COMMAND, new GoToRegistrationPageCommand());
        commands.put(UPDATE_USER_PERSONAL_INFO_COMMAND, new UpdateUserPersonalInfoCommand());
        commands.put(GO_TO_LOGIN_PAGE_COMMAND, new GoToLoginPageCommand());
        commands.put(GO_TO_ACCOUNT_PAGE_COMMAND, new GoToAccountPageCommand());
        commands.put(GO_TO_HOMEPAGE_COMMAND, new GoToHomePageCommand());
        commands.put(GO_TO_CHANGE_PERSONAL_INFO_PAGE_COMMAND, new GoToChangePersonalInfoForm());
        commands.put(GO_TO_CHANGE_PASSWORD_PAGE_COMMAND, new GoToChangePasswordForm());
        commands.put(CHANGE_PASSWORD_COMMAND, new ChangePasswordCommand());
        commands.put(GO_TO_INACTIVATE_USER_PAGE_COMMAND, new GoToInactivateUserPage());
        commands.put(INACTIVATE_USER_COMMAND, new InactivateUserCommand());
        commands.put(GO_TO_ADD_CAR_PAGE_COMMAND, new GoToAddCarPage());
        commands.put(ADD_CAR_COMMAND, new AddCarCommand());
        commands.put(GO_TO_UPDATE_CAR_PAGE_COMMAND, new GoToUpdateCarPage());
        commands.put(UPDATE_CAR_COMMAND, new UpdateCarCommand());
        commands.put(FIND_ALL_CARS_COMMAND, new FindAllCarsCommand());
        commands.put(GO_TO_INACTIVATE_CAR_PAGE_COMMAND, new GoToInactivateCarPage());
        commands.put(INACTIVATE_CAR_COMMAND, new InactivateCarCommand());


    }

    public CommandProvider() {
    }

    public static Command getCommand(String commandName) {
        Command command = commands.get(commandName);
        if (command != null) {
            return command;
        } else {
            return commands.get(DEFAULT_COMMAND);
        }
    }
}
