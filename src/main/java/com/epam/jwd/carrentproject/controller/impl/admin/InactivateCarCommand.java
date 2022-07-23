package com.epam.jwd.carrentproject.controller.impl.admin;

import com.epam.jwd.carrentproject.controller.Command;
import com.epam.jwd.carrentproject.controller.CommandException;
import com.epam.jwd.carrentproject.controller.Router;
import com.epam.jwd.carrentproject.controller.constant.PagePath;
import com.epam.jwd.carrentproject.service.CarService;
import com.epam.jwd.carrentproject.service.ServiceException;
import com.epam.jwd.carrentproject.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

import static com.epam.jwd.carrentproject.controller.constant.SessionAttributeName.*;
import static com.epam.jwd.carrentproject.controller.constant.RequestParameterName.*;

/**
 * The {@code InactivateCarCommand} class implements the functional of {@link Command}
 * The class executes the command to inactivate the exists car in the system
 *
 * @author Dmitry Murzo
 */
public class InactivateCarCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * The method executes the inactivate car command, writes an additional info to the car's data and session's
     * attributes
     */
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();

        Map<String, String> carData = (Map<String, String>) session.getAttribute(CAR_DATA_SESSION);
        removeTempData(carData);
        updateUserDataFromRequest(request, carData);
        CarService carService = ServiceProvider.getInstance().getCarService();
        Router router;

        try {
            int sizeBefore = carData.size();
            boolean result = carService.inactivateCar(carData);
            int sizeAfter = carData.size();

            if (sizeBefore != sizeAfter) {
                session.setAttribute(CAR_DATA_SESSION, carData);
            }

            session.setAttribute(INACTIVATE_CAR_RESULT, result);


            session.setAttribute(CURRENT_PAGE, PagePath.INACTIVATE_CAR_PAGE);
            router = new Router(PagePath.INACTIVATE_CAR_PAGE);


        } catch (ServiceException e) {
            LOGGER.error("Unsuccessful attempt to update car's info.", e);
            throw new CommandException("Unsuccessful attempt to update car's info.", e);

        }

        return router;
    }

    /**
     * Removes the temporary data from car's data
     *
     * @param carData the car's data
     */
    private void removeTempData(Map<String, String> carData) {
        carData.remove(WRONG_ID_SESSION);
    }

    /**
     * Updates car's data from request
     *
     * @param request a request from controller
     * @param carData the car's data
     */
    private void updateUserDataFromRequest(HttpServletRequest request, Map<String, String> carData) {
        carData.put(CAR_ID_TO_INACTIVATE_SESSION, request.getParameter(CAR_ID_TO_INACTIVATE));


    }
}
