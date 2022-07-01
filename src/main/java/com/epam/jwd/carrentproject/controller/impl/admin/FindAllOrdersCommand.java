package com.epam.jwd.carrentproject.controller.impl.admin;

import com.epam.jwd.carrentproject.controller.Command;
import com.epam.jwd.carrentproject.controller.CommandException;
import com.epam.jwd.carrentproject.controller.Router;
import com.epam.jwd.carrentproject.controller.constant.PagePath;
import com.epam.jwd.carrentproject.controller.constant.RequestAttributeName;
import com.epam.jwd.carrentproject.controller.constant.SessionAttributeName;
import com.epam.jwd.carrentproject.entity.Order;
import com.epam.jwd.carrentproject.service.OrderService;
import com.epam.jwd.carrentproject.service.ServiceException;
import com.epam.jwd.carrentproject.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.epam.jwd.carrentproject.controller.constant.SessionAttributeName.ORDER_DATA_SESSION;

public class FindAllOrdersCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        OrderService orderService = ServiceProvider.getInstance().getOrderService();
        Map<String, String> orderData = new HashMap<>();
        session.setAttribute(ORDER_DATA_SESSION, orderData);
        Router router;

        try {
            List<Order> orders = orderService.findAllOrders();
            request.setAttribute(RequestAttributeName.ORDERS_LIST, orders);
            session.setAttribute(SessionAttributeName.CURRENT_PAGE, Command.extract(request));
            router=new Router(PagePath.ALL_ORDERS_PAGE);

        } catch (ServiceException e) {
            logger.error("Try to find all orders was failed.", e);
            throw new CommandException("Try to find all orders was failed.", e);
        }
        return router;
    }
}
