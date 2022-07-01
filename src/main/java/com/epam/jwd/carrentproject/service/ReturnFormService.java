package com.epam.jwd.carrentproject.service;

import com.epam.jwd.carrentproject.entity.ReturnForm;

import java.util.List;
import java.util.Map;

public interface ReturnFormService {

    boolean createNewReturnForm(Map<String, String> returnFormData) throws ServiceException;
    List<ReturnForm> findAllReturnForms() throws ServiceException;
}
