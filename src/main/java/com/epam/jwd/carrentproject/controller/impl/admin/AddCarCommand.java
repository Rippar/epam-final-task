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

public class AddCarCommand implements Command {
    static Logger logger = LogManager.getLogger();
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
            boolean result = carService.createNewCar(carData);
            int sizeAfter = carData.size();

            if (sizeBefore == sizeAfter) {
                //session.removeAttribute(CAR_DATA_SESSION);  оставлять или нет ???

            } else {
                session.setAttribute(CAR_DATA_SESSION, carData);
            }

            session.setAttribute(ADD_CAR_RESULT, result);


            session.setAttribute(CURRENT_PAGE, PagePath.ADD_CAR_PAGE);
            router = new Router(PagePath.ADD_CAR_PAGE);


        } catch (ServiceException e) {
            logger.error("Unsuccessful attempt to add new car.", e);
            throw new CommandException("Unsuccessful attempt to add new car.", e);

        }

        return router;
    }

    private void removeTempData(Map<String, String> carData) {
        carData.remove(WRONG_CAR_BRAND_SESSION);
        carData.remove(WRONG_CAR_MODEL_SESSION);
        carData.remove(WRONG_CAR_CLASS_SESSION);
        carData.remove(WRONG_CAR_BODY_SESSION);
        carData.remove(WRONG_AUTO_TRANSMISSION_SESSION);
        carData.remove(WRONG_AIR_CONDITIONING_SESSION);
        carData.remove(WRONG_NUM_OF_DOORS_SESSION);
        carData.remove(WRONG_NUM_OF_SEATS_SESSION);
        carData.remove(WRONG_PRICE_SESSION);
    }

    private void updateUserDataFromRequest(HttpServletRequest request, Map<String, String> carData) {
        carData.put(CAR_BRAND_SESSION, request.getParameter(CAR_BRAND));
        carData.put(CAR_MODEL_SESSION, request.getParameter(CAR_MODEL));
        carData.put(CAR_CLASS_SESSION, request.getParameter(CAR_CLASS));
        carData.put(CAR_BODY_SESSION, request.getParameter(CAR_BODY));
        carData.put(AUTO_TRANSMISSION_SESSION, request.getParameter(AUTO_TRANSMISSION));
        carData.put(AIR_CONDITIONING_SESSION, request.getParameter(AIR_CONDITIONING));
        carData.put(NUM_OF_DOORS_SESSION, request.getParameter(NUM_OF_DOORS));
        carData.put(NUM_OF_SEATS_SESSION, request.getParameter(NUM_OF_SEATS));
        carData.put(RENTAL_PRICE_SESSION, request.getParameter(RENTAL_PRICE));

    }
}
