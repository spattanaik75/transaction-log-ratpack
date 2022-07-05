package com.muchbetter.transaction.transactionlog.handler;

import com.muchbetter.transaction.transactionlog.model.LoginResponse;
import com.muchbetter.transaction.transactionlog.model.User;
import com.muchbetter.transaction.transactionlog.repository.UserRepository;
import io.netty.handler.codec.http.HttpResponseStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ratpack.core.handling.Context;
import ratpack.core.jackson.internal.DefaultJsonRender;
import ratpack.test.handling.HandlingResult;
import ratpack.test.handling.RequestFixture;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TransactionHandlerTest {
    private Context mockCtx;
    private User user;

    @BeforeEach
    void setup() {
        mockCtx = mock(Context.class);
        user = new User("newToken", new BigDecimal("10000.00"), "ZAR", new ArrayList<>());
    }

    @Test
    void handle() throws Exception {
        //given
        when(mockCtx.get(User.class)).thenReturn(user);
        //when
        HandlingResult result = RequestFixture.handle(new TransactionHandler(), fixture ->
                fixture.header("Authorization", "newToken").uri("/transactions")
        );
        //then
        assertEquals(HttpResponseStatus.OK, result.getStatus().getNettyStatus());
    }
}