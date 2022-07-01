package com.epam.jwd.carrentproject.controller.impl;

import com.epam.jwd.carrentproject.controller.Command;
import com.epam.jwd.carrentproject.controller.CommandException;
import com.epam.jwd.carrentproject.controller.Router;
import com.epam.jwd.carrentproject.controller.constant.PagePath;
import com.epam.jwd.carrentproject.controller.constant.RequestAttributeName;
import com.epam.jwd.carrentproject.entity.Car;
import com.epam.jwd.carrentproject.service.OrderService;
import com.epam.jwd.carrentproject.service.ServiceException;
import com.epam.jwd.carrentproject.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;

import static com.epam.jwd.carrentproject.controller.constant.SessionAttributeName.*;
import static com.epam.jwd.carrentproject.controller.constant.RequestParameterName.*;


public class AddOrderCarCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {

        HttpSession session = request.getSession();
        OrderService orderService = ServiceProvider.getInstance().getOrderService();

        Map<String, String> paymentDetails;
        Map<String, String> orderData = (Map<String, String>) session.getAttribute(ORDER_DATA_SESSION);
        List<Car> availableCars = (List<Car>) session.getAttribute(AVAILABLE_CARS_LIST_SESSION);

        removeTempData(orderData);
        updateOrderDataFromRequest(request, orderData);
        Router router;

        try {
            int sizeBefore = orderData.size();
            boolean result = orderService.createNewOrder(availableCars, orderData);
            int sizeAfter = orderData.size();

            session.setAttribute(ORDER_DATA_SESSION, orderData);

            if (sizeBefore== sizeAfter) {
                paymentDetails = orderService.getPaymentDetails(orderData);
                updateSessionByPaymentDetails(session, paymentDetails);
                session.setAttribute(CURRENT_PAGE, PagePath.PAYMENT_FORM);
                router=new Router(PagePath.PAYMENT_FORM);

            } else {
                session.setAttribute(ADD_ORDER_RESULT, result);
                session.setAttribute(CURRENT_PAGE, PagePath.ORDER_FORM);
                router=new Router(PagePath.ORDER_FORM);
            }


        } catch (ServiceException e) {
            logger.error("Unsuccessful attempt to add new order.", e);
            throw new CommandException("Unsuccessful attempt to add new order.", e);

        }

        return router;
    }

    private void removeTempData(Map<String, String> orderData) {
        orderData.remove(WRONG_ID_SESSION);
        orderData.remove(WRONG_DATE_SESSION);
    }

    private void updateOrderDataFromRequest(HttpServletRequest request, Map<String, String> orderData) {
        orderData.put(CAR_ID_TO_ORDER_SESSION, request.getParameter(CAR_ID_TO_ORDER));

    }

    private void updateSessionByPaymentDetails(HttpSession session, Map<String, String> paymentDetails) {
        session.setAttribute(PAYMENT_SUM_SESSION, paymentDetails.get(PAYMENT_SUM_SESSION));
        session.setAttribute(PAYMENT_NAME_SESSION, paymentDetails.get(PAYMENT_NAME_SESSION));
        session.setAttribute(PAYMENT_SURNAME_SESSION, paymentDetails.get(PAYMENT_SURNAME_SESSION));
        session.setAttribute(PAYMENT_PASSPORT_NUMBER_SESSION, paymentDetails.get(PAYMENT_PASSPORT_NUMBER_SESSION));
        session.setAttribute(PAYMENT_EMAIL_SESSION, paymentDetails.get(PAYMENT_EMAIL_SESSION));
        session.setAttribute(PAYMENT_CAR_BRAND_SESSION, paymentDetails.get(PAYMENT_CAR_BRAND_SESSION));
        session.setAttribute(PAYMENT_CAR_MODEL_SESSION, paymentDetails.get(PAYMENT_CAR_MODEL_SESSION));
        session.setAttribute(PAYMENT_CAR_CLASS_SESSION, paymentDetails.get(PAYMENT_CAR_CLASS_SESSION));
        session.setAttribute(ORDER_PICK_UP_DATE_SESSION, paymentDetails.get(ORDER_PICK_UP_DATE_SESSION));
        session.setAttribute(ORDER_DROP_OFF_DATE_SESSION, paymentDetails.get(ORDER_DROP_OFF_DATE_SESSION));
    }
}
