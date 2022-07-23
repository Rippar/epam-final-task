package com.epam.jwd.carrentproject.service.impl;

import com.epam.jwd.carrentproject.dao.DAOException;
import com.epam.jwd.carrentproject.dao.DAOProvider;
import com.epam.jwd.carrentproject.dao.ReturnFormDAO;
import com.epam.jwd.carrentproject.entity.ReturnForm;
import com.epam.jwd.carrentproject.service.ReturnFormService;
import com.epam.jwd.carrentproject.service.ServiceException;
import com.epam.jwd.carrentproject.service.validator.ReturnFormValidator;
import com.epam.jwd.carrentproject.service.validator.ValidatorProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static com.epam.jwd.carrentproject.controller.constant.SessionAttributeName.*;

/**
 * The {@code ReturnFormServiceImpl} class implements the functional of {@link ReturnFormService}
 * The class implements the business-logic methods for working with the {@link ReturnForm} objects
 *
 * @author Dmitry Murzo
 */
public class ReturnFormServiceImpl implements ReturnFormService {

    private static final double ZERO_VALUE = 0.0;
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public boolean createNewReturnForm(Map<String, String> returnFormData) throws ServiceException {
        boolean isCreated = false;
        ReturnFormDAO returnFormDAO = DAOProvider.getInstance().getReturnFormDAO();
        ReturnFormValidator validator = ValidatorProvider.getInstance().getReturnFormValidator();

        if (!validator.validateReturnFormData(returnFormData)) {
            return isCreated;
        }

        int orderId = Integer.parseInt(returnFormData.get(ORDER_ID_SESSION));
        LocalDate currentDate = LocalDate.now();
        String damageDescription = returnFormData.get(DAMAGE_DESCRIPTION_SESSION);
        BigDecimal billValue = BigDecimal.valueOf(ZERO_VALUE);

        if (!returnFormData.get(BILL_VALUE_SESSION).isEmpty()) {
            billValue = BigDecimal.valueOf(Double.parseDouble(returnFormData.get(BILL_VALUE_SESSION)));
        }


        ReturnForm returnForm = new ReturnForm.ReturnFormBuilder()
                .withOrderId(orderId)
                .withDateOfReturn(currentDate)
                .withDamageDescription(damageDescription)
                .withBillValue(billValue)
                .build();

        try {
            isCreated = returnFormDAO.add(returnForm);
        } catch (DAOException e) {
            LOGGER.error("Unsuccessful attempt to create new return form.", e);
            throw new ServiceException("Unsuccessful attempt to create new return form.", e);
        }

        return isCreated;
    }

    @Override
    public List<ReturnForm> findAllReturnForms() throws ServiceException {
        ReturnFormDAO returnFormDAO = DAOProvider.getInstance().getReturnFormDAO();
        List<ReturnForm> returnForms;

        try {
            returnForms = returnFormDAO.findAll();
        } catch (DAOException e) {
            LOGGER.error("Unsuccessful attempt to find all return forms.", e);
            throw new ServiceException("Unsuccessful attempt to find all return forms.", e);
        }

        return returnForms;
    }
}
