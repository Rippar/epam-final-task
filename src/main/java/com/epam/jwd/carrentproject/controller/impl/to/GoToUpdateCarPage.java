package com.epam.jwd.carrentproject.controller.impl.to;

import com.epam.jwd.carrentproject.controller.Command;
import com.epam.jwd.carrentproject.controller.CommandException;
import com.epam.jwd.carrentproject.controller.Router;
import com.epam.jwd.carrentproject.controller.constant.PagePath;
import com.epam.jwd.carrentproject.controller.constant.SessionAttributeName;
import com.epam.jwd.carrentproject.entity.Car;
import com.epam.jwd.carrentproject.service.CarService;
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

public class GoToUpdateCarPage implements Command {

    private static final Logger logger = LogManager.getLogger();
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        session.removeAttribute(UPDATE_CAR_RESULT);

        CarService carService = ServiceProvider.getInstance().getCarService();

        List<Car> cars;

        try {
            cars = carService.findAllCars();
            session.setAttribute(CARS_LIST_TO_UPDATE_SESSION, cars);

        } catch (ServiceException e) {
            logger.error("Try to find all cars was failed.", e);
            throw new CommandException("Try to find all cars was failed.", e);
        }

        Map<String, String> carData = new HashMap<>();
        session.setAttribute(SessionAttributeName.CAR_DATA_SESSION, carData);

        String currentPage = Command.extract(request);
        session.setAttribute(SessionAttributeName.CURRENT_PAGE, currentPage);

        return new Router(PagePath.UPDATE_CAR_PAGE);
    }
}
