package com.epam.jwd.carrentproject.service;

import com.epam.jwd.carrentproject.dao.DAOException;
import com.epam.jwd.carrentproject.entity.ReturnForm;

import java.util.List;
import java.util.Map;

/**
 * The {@code ReturnFormService} interface defines the business-logic methods for working with the {@link ReturnForm}
 * objects
 *
 * @author Dmitry Murzo
 */
public interface ReturnFormService {

    /**
     * Creates new return form according to the given returnFormData data
     *
     * @param returnFormData the return form's data
     * @return return form's creation result
     * @throws ServiceException - if the involved DAO-method throws {@link DAOException}
     */
    boolean createNewReturnForm(Map<String, String> returnFormData) throws ServiceException;

    /**
     * Finds all return form's exists in the system
     *
     * @return the list of return form's
     * @throws ServiceException - if the involved DAO-method throws {@link DAOException}
     */
    List<ReturnForm> findAllReturnForms() throws ServiceException;
}
