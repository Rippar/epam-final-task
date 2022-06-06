package com.epam.jwd.webproject.service.impl;

import com.epam.jwd.webproject.dao.DAOException;
import com.epam.jwd.webproject.dao.DAOProvider;
import com.epam.jwd.webproject.dao.UserDAO;
import com.epam.jwd.webproject.service.ServiceException;
import com.epam.jwd.webproject.service.UserService;

public class UserServiceImpl implements UserService {
    @Override
    public boolean authentication(String login, String password) throws ServiceException {
        //validate login, pass +MD5
        UserDAO userDAO = DAOProvider.getInstance().getUserDAO();
        boolean result;
        try {
            result = userDAO.authentication(login, password);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return result;
    }
}
