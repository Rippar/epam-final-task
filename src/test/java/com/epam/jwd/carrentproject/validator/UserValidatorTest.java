package com.epam.jwd.carrentproject.validator;

import com.epam.jwd.carrentproject.service.validator.UserValidator;
import com.epam.jwd.carrentproject.service.validator.ValidatorProvider;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class UserValidatorTest {


    private UserValidator userValidator;

    @BeforeMethod
    public void initTest() {
        userValidator = ValidatorProvider.getInstance().getUserValidator();
        Assert.assertNotNull(userValidator);
    }


    @DataProvider(name = "emailProvider")
    public Object[][] createEmailData() {
        return new Object[][] {
                {"ivanov@gmail.com", true},
                {"iv@gmail.com", false},
                {"ivanov@gmail.com.com", false}
        };
    }

    @Test(dataProvider = "emailProvider")
    public void emailValidationTest(String email, boolean expected) {
        boolean isValid = userValidator.validateEmail(email);
        Assert.assertEquals(isValid, expected);
    }

    @DataProvider(name = "loginProvider")
    public Object[][] createLoginData() {
        return new Object[][] {
                {"Rippar", true},
                {"TheLongestLoginInTHeWorld", false},
                {"xx", false}
        };
    }

    @Test(dataProvider = "loginProvider")
    public void loginValidationTest(String login, boolean expected) {
        boolean isValid = userValidator.validateLogin(login);
        Assert.assertEquals(isValid, expected);
    }

    @DataProvider(name = "passwordProvider")
    public Object[][] createPasswordData() {
        return new Object[][] {
                {"1234", false},
                {"PassWithoutNumbers", false},
                {"123456AaB", true}
        };
    }

    @Test(dataProvider = "passwordProvider")
    public void passwordValidationTest(String password, boolean expected) {
        boolean isValid = userValidator.validatePassword(password);
        Assert.assertEquals(isValid, expected);
    }

    @DataProvider(name = "passportProvider")
    public Object[][] createPassportData() {
        return new Object[][] {
                {"EN1234567", false},
                {"УГ2345678", true},
                {"УГ23456788", false}
        };
    }

    @Test(dataProvider = "passportProvider")
    public void passportValidationTest(String passport, boolean expected) {
        boolean isValid = userValidator.validatePassportNum(passport);
        Assert.assertEquals(isValid, expected);
    }


}
