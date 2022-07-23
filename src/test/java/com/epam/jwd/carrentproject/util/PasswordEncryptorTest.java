package com.epam.jwd.carrentproject.util;


import org.testng.Assert;
import org.testng.annotations.Test;

public class PasswordEncryptorTest {
    @Test
    public void encryptPositiveTest() {
        String pass0 = "123456TestT";
        String pass1 = "123456TestT";

        String hash0 = PasswordEncryptor.md5Apache(pass0);
        String hash1 = PasswordEncryptor.md5Apache(pass1);

        Assert.assertTrue(hash0.equals(hash1));
    }

    @Test
    public void encryptNegativeTest() {
        String pass0 = "123456TestT";
        String pass1 = "123456Test";

        String hash0 = PasswordEncryptor.md5Apache(pass0);
        String hash1 = PasswordEncryptor.md5Apache(pass1);

        Assert.assertFalse(hash0.equals(hash1));
    }
}
