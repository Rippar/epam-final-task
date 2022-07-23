package com.epam.jwd.carrentproject.util;

import org.apache.commons.codec.digest.DigestUtils;


/**
 * The {@code PasswordEncryptor} class contains a method to encrypt a password
 *
 * @author Dmitry Murzo
 */
public class PasswordEncryptor {

    /**
     * Encrypts a password using md5 message-digest algorithm
     *
     * @param password a password
     * @return a string representation of the encrypted password
     */
    public static String md5Apache(String password) {
        String md5Hex = DigestUtils.md5Hex(password);
        return md5Hex;
    }
}