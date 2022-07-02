package com.muchbetter.transaction.transactionlog.utility;

import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public class TokenUtil {
    public static String getToken(){
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }
}
