package com.muchbetter.transaction.transactionlog.utility;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TokenUtilTest {

    @Test
    void getToken() {
        assertNotNull(TokenUtil.getToken());
    }
}