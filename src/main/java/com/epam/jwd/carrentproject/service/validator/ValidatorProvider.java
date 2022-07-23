package com.epam.jwd.carrentproject.service.validator;

import com.epam.jwd.carrentproject.service.validator.impl.CarValidatorImpl;
import com.epam.jwd.carrentproject.service.validator.impl.OrderValidatorImpl;
import com.epam.jwd.carrentproject.service.validator.impl.ReturnFormValidatorImpl;
import com.epam.jwd.carrentproject.service.validator.impl.UserValidatorImpl;

/**
 * The {@code ValidatorProvider} class uses singleton pattern to control the number of validator objects created. Hides
 * the validator's implementations. Also makes it possible to change this implementations if necessary.
 *
 * @author Dmitry Murzo
 */
public class ValidatorProvider {

    private static final ValidatorProvider INSTANCE = new ValidatorProvider();

    private CarValidator carValidator = new CarValidatorImpl();

    private OrderValidator orderValidator = new OrderValidatorImpl();

    private ReturnFormValidator returnFormValidator = new ReturnFormValidatorImpl();

    private UserValidator userValidator = new UserValidatorImpl();

    private ValidatorProvider() {

    }

    public static ValidatorProvider getInstance() {
        return INSTANCE;
    }

    public CarValidator getCarValidator() {
        return carValidator;
    }

    public void setCarValidator(CarValidator carValidator) {
        this.carValidator = carValidator;
    }

    public OrderValidator getOrderValidator() {
        return orderValidator;
    }

    public void setOrderValidator(OrderValidator orderValidator) {
        this.orderValidator = orderValidator;
    }

    public ReturnFormValidator getReturnFormValidator() {
        return returnFormValidator;
    }

    public void setReturnFormValidator(ReturnFormValidator returnFormValidator) {
        this.returnFormValidator = returnFormValidator;
    }

    public UserValidator getUserValidator() {
        return userValidator;
    }

    public void setUserValidator(UserValidator userValidator) {
        this.userValidator = userValidator;
    }
}
