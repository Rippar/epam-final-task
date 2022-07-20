package com.epam.jwd.carrentproject.controller.impl.to;

import com.epam.jwd.carrentproject.controller.Command;
import com.epam.jwd.carrentproject.controller.CommandException;
import com.epam.jwd.carrentproject.controller.Router;
import com.epam.jwd.carrentproject.controller.constant.PagePath;
import com.epam.jwd.carrentproject.controller.constant.SessionAttributeName;
import com.epam.jwd.carrentproject.entity.Order;
import com.epam.jwd.carrentproject.entity.OrderStatus;
import com.epam.jwd.carrentproject.service.OrderService;
import com.epam.jwd.carrentproject.service.ServiceException;
import com.epam.jwd.carrentproject.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;


public class GoToCancelOrdersPage implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        OrderService orderService = ServiceProvider.getInstance().getOrderService();
        session.removeAttribute(SessionAttributeName.CANCEL_ORDER_RESULT);

        List<Order> orders = new ArrayList<>();

        try {
            orders.addAll(orderService.findAllOrdersByStatus(OrderStatus.BOOKED_STATUS));
            orders.addAll(orderService.findAllOrdersByStatus(OrderStatus.CONFIRMED_STATUS));

        } catch (ServiceException e) {
            logger.error("Unsuccessful attempt to get the list of orders by status.", e);
            throw new CommandException("Unsuccessful attempt to get the list of orders by status.", e);
        }

        String currentPage = Command.extract(request);
        session.setAttribute(SessionAttributeName.CURRENT_PAGE, currentPage);
        session.setAttribute(SessionAttributeName.BOOKED_CONFIRMED_ORDERS_SESSION, orders);
        return new Router(PagePath.CANCEL_ORDERS_PAGE);
    }
}
