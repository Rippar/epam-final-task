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

/**
 * The {@code FindAllOrdersCommand} class implements the functional of {@link Command}
 * The class executes the command to find all the exists orders in the system
 *
 * @author Dmitry Murzo
 */
public class FindAllOrdersCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * The method executes the find all orders command, writes an additional info to the request's and session's
     * attributes
     */
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
            router = new Router(PagePath.ALL_ORDERS_PAGE);

        } catch (ServiceException e) {
            LOGGER.error("Try to find all orders was failed.", e);
            throw new CommandException("Try to find all orders was failed.", e);
        }
        return router;
    }
}
