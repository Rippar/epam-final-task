package com.epam.jwd.carrentproject.controller.impl.admin;

import com.epam.jwd.carrentproject.controller.Command;
import com.epam.jwd.carrentproject.controller.CommandException;
import com.epam.jwd.carrentproject.controller.Router;
import com.epam.jwd.carrentproject.controller.constant.PagePath;
import com.epam.jwd.carrentproject.controller.constant.RequestAttributeName;
import com.epam.jwd.carrentproject.controller.constant.SessionAttributeName;
import com.epam.jwd.carrentproject.entity.Car;
import com.epam.jwd.carrentproject.service.CarService;
import com.epam.jwd.carrentproject.service.ServiceException;
import com.epam.jwd.carrentproject.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class FindAllCarsCommand implements Command {

    static Logger logger = LogManager.getLogger();
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        CarService carService = ServiceProvider.getInstance().getCarService();
        Router router;

        try {
            List<Car> cars = carService.findAllCars();
            request.setAttribute(RequestAttributeName.CARS_LIST, cars);
            session.setAttribute(SessionAttributeName.CURRENT_PAGE, Command.extract(request));
            router = new Router(PagePath.ALL_CARS_PAGE);

        } catch (ServiceException e) {
            logger.error("Try to execute FindAllCarsCommand was failed.", e);
            throw new CommandException("Try to execute FindAllCarsCommand was failed.", e);
        }
        return router;
    }
}
