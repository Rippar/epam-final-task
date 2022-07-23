package com.epam.jwd.carrentproject.controller;

import com.epam.jwd.carrentproject.controller.impl.*;
import com.epam.jwd.carrentproject.controller.impl.admin.*;
import com.epam.jwd.carrentproject.controller.impl.to.*;


import java.util.HashMap;
import java.util.Map;

import static com.epam.jwd.carrentproject.controller.constant.CommandName.*;

/**
 * The {@code CommandProvider} class contains the collection of available Command objects and allows to receive them.
 *
 * @author Dmitry Murzo
 */

public class CommandProvider {

    /**
     * Map is used to store and receive the Command objects by String key (the name of command)
     */
    private static Map<String, Command> commands;

    static {

        commands = new HashMap<>();

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
        commands.put(GO_TO_CHANGE_PERSONAL_INFO_PAGE_COMMAND, new GoToChangePersonalInfoPageCommand());
        commands.put(GO_TO_CHANGE_PASSWORD_PAGE_COMMAND, new GoToChangePasswordPageCommand());
        commands.put(CHANGE_PASSWORD_COMMAND, new ChangePasswordCommand());
        commands.put(GO_TO_INACTIVATE_USER_PAGE_COMMAND, new GoToInactivateUserPageCommand());
        commands.put(INACTIVATE_USER_COMMAND, new InactivateUserCommand());
        commands.put(GO_TO_ADD_CAR_PAGE_COMMAND, new GoToAddCarPageCommand());
        commands.put(ADD_CAR_COMMAND, new AddCarCommand());
        commands.put(GO_TO_UPDATE_CAR_PAGE_COMMAND, new GoToUpdateCarPageCommand());
        commands.put(UPDATE_CAR_COMMAND, new UpdateCarCommand());
        commands.put(FIND_ALL_CARS_COMMAND, new FindAllCarsCommand());
        commands.put(GO_TO_INACTIVATE_CAR_PAGE_COMMAND, new GoToInactivateCarPageCommand());
        commands.put(INACTIVATE_CAR_COMMAND, new InactivateCarCommand());
        commands.put(GO_TO_ORDER_PAGE_COMMAND, new GoToOrderPageCommand());
        commands.put(ADD_ORDER_COMMAND, new AddOrderCarCommand());
        commands.put(GO_TO_USER_ORDERS_PAGE_COMMAND, new GoToUserOrdersPageCommand());
        commands.put(INACTIVATE_ORDER_COMMAND, new InactivateOrderCommand());
        commands.put(FIND_ALL_ORDERS_COMMAND, new FindAllOrdersCommand());
        commands.put(GO_TO_CONFIRM_ORDERS_PAGE_COMMAND, new GoToConfirmOrdersPageCommand());
        commands.put(CONFIRM_ORDER_COMMAND, new ConfirmOrderCommand());
        commands.put(GO_TO_CANCEL_ORDERS_PAGE_COMMAND, new GoToCancelOrdersPageCommand());
        commands.put(CANCEL_ORDER_COMMAND, new CancelOrderCommand());
        commands.put(GO_TO_COMPLETE_ORDERS_PAGE_COMMAND, new GoToCompleteOrdersPageCommand());
        commands.put(COMPLETE_ORDER_COMMAND, new CompleteOrderCommand());
        commands.put(FIND_ALL_RETURN_FORMS_COMMAND, new FindAllReturnFormsCommand());
        commands.put(CHANGE_LANGUAGE_COMMAND, new ChangeLanguageCommand());

    }

    private CommandProvider() {
    }

    /**
     * Getter method is used to receive the Command object by the name of this command
     *
     * @param commandName is the String key associated with specific Command object in the map
     * @return Command object
     */

    public static Command getCommand(String commandName) {
        Command command = commands.get(commandName);

        if (command != null) {
            return command;

        } else {
            return commands.get(DEFAULT_COMMAND);
        }
    }
}
