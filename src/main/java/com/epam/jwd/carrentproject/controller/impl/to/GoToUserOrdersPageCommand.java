package com.epam.jwd.carrentproject.controller.impl.to;

import com.epam.jwd.carrentproject.controller.Command;
import com.epam.jwd.carrentproject.controller.CommandException;
import com.epam.jwd.carrentproject.controller.Router;
import com.epam.jwd.carrentproject.controller.constant.PagePath;
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

import static com.epam.jwd.carrentproject.controller.constant.SessionAttributeName.*;

/**
 * The {@code GoToUserOrdersPage} class implements the functional of {@link Command}
 * The class executes the command to go to the user's orders page
 *
 * @author Dmitry Murzo
 */
public class GoToUserOrdersPageCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * The method executes the command to go to the user's orders page, writes an additional
     * info to the session's attributes
     */
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        OrderService orderService = ServiceProvider.getInstance().getOrderService();

        Map<String, String> orderData = new HashMap<>();
        session.setAttribute(ORDER_DATA_SESSION, orderData);
        session.removeAttribute(CANCEL_ORDER_RESULT);

        List<Order> userOrders;

        try {
            userOrders = orderService.findAllOrdersByUserId((Integer) session.getAttribute(USER_ID_SESSION));
        } catch (ServiceException e) {
            LOGGER.error("Unsuccessful attempt to get the list of user's orders.", e);
            throw new CommandException("Unsuccessful attempt to get the list of user's orders.", e);
        }
        session.setAttribute(USER_ORDERS_SESSION, userOrders);

        String currentPage = Command.extract(request);
        session.setAttribute(SessionAttributeName.CURRENT_PAGE, currentPage);

        return new Router(PagePath.USER_ORDERS_PAGE);
    }
}
