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

import java.util.List;

import static com.epam.jwd.carrentproject.controller.constant.SessionAttributeName.CONFIRM_ORDER_RESULT;

/**
 * The {@code GoToConfirmOrdersPage} class implements the functional of {@link Command}
 * The class executes the command to go to the confirm orders page
 *
 * @author Dmitry Murzo
 */
public class GoToConfirmOrdersPageCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * The method executes the command to go to the confirm orders page, writes an additional
     * info to the session's attributes
     */
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        session.removeAttribute(CONFIRM_ORDER_RESULT);
        OrderService orderService = ServiceProvider.getInstance().getOrderService();

        List<Order> orders;

        try {
            orders = orderService.findAllOrdersByStatus(OrderStatus.BOOKED_STATUS);

        } catch (ServiceException e) {
            LOGGER.error("Unsuccessful attempt to get the list of orders by status.", e);
            throw new CommandException("Unsuccessful attempt to get the list of orders by status.", e);
        }

        String currentPage = Command.extract(request);
        session.setAttribute(SessionAttributeName.CURRENT_PAGE, currentPage);
        session.setAttribute(SessionAttributeName.BOOKED_ORDERS_SESSION, orders);

        return new Router(PagePath.CONFIRM_ORDERS_PAGE);
    }
}
