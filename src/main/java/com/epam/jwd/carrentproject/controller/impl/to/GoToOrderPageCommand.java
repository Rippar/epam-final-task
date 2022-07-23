package com.epam.jwd.carrentproject.controller.impl.to;

import com.epam.jwd.carrentproject.controller.Command;
import com.epam.jwd.carrentproject.controller.CommandException;
import com.epam.jwd.carrentproject.controller.Router;
import com.epam.jwd.carrentproject.controller.constant.PagePath;
import com.epam.jwd.carrentproject.controller.constant.SessionAttributeName;
import com.epam.jwd.carrentproject.entity.Car;
import com.epam.jwd.carrentproject.service.CarService;
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

import static com.epam.jwd.carrentproject.controller.constant.RequestParameterName.*;
import static com.epam.jwd.carrentproject.controller.constant.SessionAttributeName.*;

/**
 * The {@code GoToOrderPage} class implements the functional of {@link Command}
 * The class executes the command to go to the order page
 *
 * @author Dmitry Murzo
 */
public class GoToOrderPageCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * The method executes the command to go to the order page, writes an additional
     * info to the order's data and session's attributes
     */
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        OrderService orderService = ServiceProvider.getInstance().getOrderService();
        CarService carService = ServiceProvider.getInstance().getCarService();
        Map<String, String> orderData = new HashMap<>();
        updateOrderDataFromRequest(request, orderData);
        session.setAttribute(ORDER_DATA_SESSION, orderData);

        if (orderService.checkTheOrderDates(orderData)) {

            List<Car> availableCarsList;

            try {
                availableCarsList = carService.findAllAvailableCars(orderData);
            } catch (ServiceException e) {
                LOGGER.error("Unsuccessful attempt to get the list of available cars.", e);
                throw new CommandException("Unsuccessful attempt to get the list of available cars.", e);
            }

            session.setAttribute(AVAILABLE_CARS_LIST_SESSION, availableCarsList);
            orderData.put(USER_ID_SESSION, String.valueOf(session.getAttribute(USER_ID_SESSION)));

            String currentPage = Command.extract(request);
            session.setAttribute(SessionAttributeName.CURRENT_PAGE, currentPage);

            return new Router(PagePath.ORDER_PAGE);

        } else {

            return new Router(PagePath.HOME_PAGE);
        }

    }

    /**
     * Updates order's data from request
     *
     * @param request   a request from controller
     * @param orderData the order's data
     */
    private void updateOrderDataFromRequest(HttpServletRequest request, Map<String, String> orderData) {
        orderData.put(ORDER_PICK_UP_DATE_SESSION, request.getParameter(PICK_UP_DATE));
        orderData.put(ORDER_DROP_OFF_DATE_SESSION, request.getParameter(DROP_OFF_DATE));

    }
}
