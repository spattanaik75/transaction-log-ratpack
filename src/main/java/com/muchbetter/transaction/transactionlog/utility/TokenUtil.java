package com.muchbetter.transaction.transactionlog.utility;

import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public class TokenUtil {
    /**
     * It generates a random string of 32 characters, which is a combination of numbers and letters
     *
     * @return A random string of characters.
     */
    public static String getToken() {
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }
}
