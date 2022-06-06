package com.epam.jwd.webproject.service.impl;

import com.epam.jwd.webproject.dao.DAOProvider;
import com.epam.jwd.webproject.dao.UserDAO;
import com.epam.jwd.webproject.service.UserService;

public class UserServiceImpl implements UserService {
    @Override
    public boolean authentication(String login, String password) {
        //validate login, pass +MD5
        UserDAO userDAO = DAOProvider.getInstance().getUserDAO();
        boolean result = userDAO.authentication(login, password);
        return result;
    }
}
