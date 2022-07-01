package com.epam.jwd.carrentproject.controller.impl.admin;

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

public class ConfirmOrderCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {

        HttpSession session = request.getSession();
        OrderService orderService = ServiceProvider.getInstance().getOrderService();

        Map<String, String> orderData = (Map<String, String>) session.getAttribute(ORDER_DATA_SESSION);
        List<Order> bookedOrders = (List<Order>) session.getAttribute(BOOKED_ORDERS_SESSION);
        orderData.put(ORDER_STATUS_SESSION, OrderStatus.CONFIRMED_STATUS);

        removeTempData(orderData);
        updateOrderDataFromRequest(request, orderData);
        Router router;

        try {
            int sizeBefore = orderData.size();
            boolean result = orderService.changeOrderStatus(bookedOrders, orderData);
            int sizeAfter = orderData.size();

            session.setAttribute(CONFIRM_ORDER_RESULT, result);

            if (sizeBefore!= sizeAfter) {
                session.setAttribute(ORDER_DATA_SESSION, orderData);

            }

            session.setAttribute(CURRENT_PAGE, PagePath.CONFIRM_ORDERS_PAGE);
            router = new Router(PagePath.CONFIRM_ORDERS_PAGE);


        } catch (ServiceException e) {
            logger.error("Unsuccessful attempt to change order's status.", e);
            throw new CommandException("Unsuccessful attempt to change order's status.", e);

        }

        return router;

    }

    private void removeTempData(Map<String, String> orderData) {
        orderData.remove(WRONG_ID_SESSION);
    }

    private void updateOrderDataFromRequest(HttpServletRequest request, Map<String, String> orderData) {
        orderData.put(ORDER_ID_SESSION, request.getParameter(ORDER_ID));

    }
}
