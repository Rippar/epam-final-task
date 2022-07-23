package com.epam.jwd.carrentproject.controller.impl;

import com.epam.jwd.carrentproject.controller.Command;
import com.epam.jwd.carrentproject.controller.CommandException;
import com.epam.jwd.carrentproject.controller.Router;
import com.epam.jwd.carrentproject.controller.constant.PagePath;
import com.epam.jwd.carrentproject.entity.Order;
import com.epam.jwd.carrentproject.entity.OrderStatus;
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

/**
 * The {@code InactivateOrderCommand} class implements the functional of {@link Command}
 * The class executes the command to inactivate an order in the system
 *
 * @author Dmitry Murzo
 */
public class InactivateOrderCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * The method executes the inactivate order command, writes an additional info to the order's data and session's
     * attributes
     */
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {

        HttpSession session = request.getSession();
        OrderService orderService = ServiceProvider.getInstance().getOrderService();

        Map<String, String> orderData = (Map<String, String>) session.getAttribute(ORDER_DATA_SESSION);
        List<Order> availableOrders = (List<Order>) session.getAttribute(USER_ORDERS_SESSION);
        orderData.put(ORDER_STATUS_SESSION, OrderStatus.CANCELED_STATUS);

        removeTempData(orderData);
        updateOrderDataFromRequest(request, orderData);
        Router router;

        try {
            int sizeBefore = orderData.size();
            boolean result = orderService.changeOrderStatus(availableOrders, orderData);
            int sizeAfter = orderData.size();

            session.setAttribute(CANCEL_ORDER_RESULT, result);

            if (sizeBefore != sizeAfter) {
                session.setAttribute(ORDER_DATA_SESSION, orderData);

            }

            session.setAttribute(CURRENT_PAGE, PagePath.USER_ORDERS_PAGE);
            router = new Router(PagePath.USER_ORDERS_PAGE);


        } catch (ServiceException e) {
            LOGGER.error("Unsuccessful attempt to inactivate order.", e);
            throw new CommandException("Unsuccessful attempt to inactivate order.", e);

        }

        return router;

    }

    /**
     * Removes the temporary data from order's data
     *
     * @param orderData the order's data
     */
    private void removeTempData(Map<String, String> orderData) {
        orderData.remove(WRONG_ID_SESSION);
    }

    /**
     * Updates order's data from request
     *
     * @param request   a request from controller
     * @param orderData the order's data
     */
    private void updateOrderDataFromRequest(HttpServletRequest request, Map<String, String> orderData) {
        orderData.put(ORDER_ID_SESSION, request.getParameter(ORDER_ID));

    }
}
