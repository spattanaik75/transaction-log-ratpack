package com.muchbetter.transaction.transactionlog.handler;

import com.muchbetter.transaction.transactionlog.model.LoginResponse;
import com.muchbetter.transaction.transactionlog.model.User;
import com.muchbetter.transaction.transactionlog.repository.UserRepository;
import com.muchbetter.transaction.transactionlog.repository.impl.UserRepositoryInMemory;
import io.netty.handler.codec.http.HttpResponseStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ratpack.core.jackson.internal.DefaultJsonRender;
import ratpack.test.handling.HandlingResult;
import ratpack.test.handling.RequestFixture;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AuthHandlerTest {
    private UserRepository mockUserRepository;
    private User user;

    @BeforeEach
    void setup() {
        mockUserRepository = mock(UserRepository.class);
        user = new User("newToken", new BigDecimal("10000.00"), "ZAR", new ArrayList<>());
    }

    @Test
    void testValidTokenAndUnauthorizedUser() throws Exception {
        //given
        when(mockUserRepository.findByToken(any())).thenReturn(null);
        //when
        HandlingResult result = RequestFixture.handle(new AuthHandler(mockUserRepository), fixture ->
                fixture.header("Authorization", "newToken")
        );
        //then
        assertEquals(HttpResponseStatus.FORBIDDEN, result.getStatus().getNettyStatus());
    }


    @Test
    void testWithNoToken() throws Exception {
        //given
        when(mockUserRepository.findByToken(any())).thenReturn(null);
        //when
        HandlingResult result = RequestFixture.handle(new AuthHandler(mockUserRepository), fixture ->
                fixture.header("testHeader", "testValue")
        );
        //then
        assertEquals(HttpResponseStatus.UNAUTHORIZED, result.getStatus().getNettyStatus());
    }
}