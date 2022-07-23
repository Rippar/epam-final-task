package com.epam.jwd.carrentproject.controller.impl.admin;

import com.epam.jwd.carrentproject.controller.Command;
import com.epam.jwd.carrentproject.controller.CommandException;
import com.epam.jwd.carrentproject.controller.Router;
import com.epam.jwd.carrentproject.controller.constant.PagePath;
import com.epam.jwd.carrentproject.entity.Order;
import com.epam.jwd.carrentproject.entity.OrderStatus;
import com.epam.jwd.carrentproject.service.OrderService;
import com.epam.jwd.carrentproject.service.ReturnFormService;
import com.epam.jwd.carrentproject.service.ServiceException;
import com.epam.jwd.carrentproject.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.epam.jwd.carrentproject.controller.constant.SessionAttributeName.*;
import static com.epam.jwd.carrentproject.controller.constant.RequestParameterName.*;

/**
 * The {@code CompleteOrderCommand} class implements the functional of {@link Command}
 * The class executes the command to make the exists order completed and creates a new
 * {@link com.epam.jwd.carrentproject.entity.ReturnForm} object in the system
 *
 * @author Dmitry Murzo
 */
public class CompleteOrderCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * The method executes the complete order command, writes an additional info to the order's data and session's
     * attributes
     */
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        OrderService orderService = ServiceProvider.getInstance().getOrderService();
        ReturnFormService returnFormService = ServiceProvider.getInstance().getReturnFormService();


        Map<String, String> returnFormData = new HashMap<>();
        session.setAttribute(RETURN_FORM_DATA_SESSION, returnFormData);

        Map<String, String> orderData = (Map<String, String>) session.getAttribute(ORDER_DATA_SESSION);
        List<Order> confirmedOrders = (List<Order>) session.getAttribute(CONFIRMED_ORDERS_SESSION);
        orderData.put(ORDER_STATUS_SESSION, OrderStatus.COMPLETED_STATUS);

        removeSessionResults(session);
        removeTempData(orderData, returnFormData);

        updateOrderDataFromRequest(request, orderData, returnFormData);
        Router router;

        try {
            int orderDataSizeBefore = orderData.size();
            boolean completeOrderResult = orderService.changeOrderStatus(confirmedOrders, orderData);
            int orderDataSizeAfter = orderData.size();

            session.setAttribute(COMPLETE_ORDER_RESULT, completeOrderResult);

            if (orderDataSizeBefore != orderDataSizeAfter) {
                session.setAttribute(ORDER_DATA_SESSION, orderData);
                session.setAttribute(CURRENT_PAGE, PagePath.COMPLETE_ORDERS_PAGE);

                session.setAttribute(CURRENT_PAGE, PagePath.COMPLETE_ORDERS_PAGE);
                return new Router(PagePath.COMPLETE_ORDERS_PAGE);

            }


        } catch (ServiceException e) {
            LOGGER.error("Unsuccessful attempt to change the order's status as complete.", e);
            throw new CommandException("Unsuccessful attempt to change the order's status as complete.", e);

        }

        try {
            int returnFormDataSizeBefore = returnFormData.size();
            boolean addReturnFormResult = returnFormService.createNewReturnForm(returnFormData);
            int returnFormDataSizeAfter = returnFormData.size();

            session.setAttribute(ADD_RETURN_FORM_RESULT, addReturnFormResult);

            if (returnFormDataSizeBefore != returnFormDataSizeAfter) {
                session.setAttribute(RETURN_FORM_DATA_SESSION, returnFormData);
                session.setAttribute(CURRENT_PAGE, PagePath.COMPLETE_ORDERS_PAGE);

            }

            session.setAttribute(CURRENT_PAGE, PagePath.COMPLETE_ORDERS_PAGE);
            router = new Router(PagePath.COMPLETE_ORDERS_PAGE);

        } catch (ServiceException e) {
            LOGGER.error("Unsuccessful attempt to create the return form to the order.", e);
            throw new CommandException("Unsuccessful attempt to create the return form to the order.", e);

        }

        return router;

    }

    /**
     * Removes the temporary data from order's data and return form's data
     *
     * @param orderData      the order's data
     * @param returnFormData the return form's data
     */
    private void removeTempData(Map<String, String> orderData, Map<String, String> returnFormData) {
        orderData.remove(WRONG_ID_SESSION);
        returnFormData.remove(WRONG_DESCRIPTION_SESSION);
        returnFormData.remove(WRONG_BILL_VALUE_SESSION);

    }

    /**
     * Updates order's data and return form's data from request
     *
     * @param request        a request from controller
     * @param orderData      the order's data
     * @param returnFormData the return form's data
     */
    private void updateOrderDataFromRequest(HttpServletRequest request, Map<String, String> orderData, Map<String,
            String> returnFormData) {

        orderData.put(ORDER_ID_SESSION, request.getParameter(ORDER_ID));
        returnFormData.put(ORDER_ID_SESSION, request.getParameter(ORDER_ID));
        returnFormData.put(DAMAGE_DESCRIPTION_SESSION, request.getParameter(DAMAGE_DESCRIPTION));
        returnFormData.put(BILL_VALUE_SESSION, request.getParameter(BILL_VALUE));

    }

    /**
     * Removes the results of the commands from session's attributes
     *
     * @param session a session
     */
    private void removeSessionResults(HttpSession session) {
        session.removeAttribute(COMPLETE_ORDER_RESULT);
        session.removeAttribute(ADD_RETURN_FORM_RESULT);
    }
}
