package com.muchbetter.transaction.transactionlog.handler;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.muchbetter.transaction.transactionlog.model.LoginResponse;
import com.muchbetter.transaction.transactionlog.model.User;
import com.muchbetter.transaction.transactionlog.repository.UserRepository;
import io.netty.handler.codec.http.HttpResponseStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import ratpack.core.jackson.internal.DefaultJsonRender;
import ratpack.test.handling.HandlingResult;
import ratpack.test.handling.RequestFixture;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LoginHandlerTest {
    private UserRepository mockUserRepository;
    private User user;

    @BeforeEach
    void setup() {
        mockUserRepository = mock(UserRepository.class);
        user = new User("newToken", new BigDecimal("10000.00"), "ZAR", new ArrayList<>());
    }

    @Test
    void handleTest() throws Exception {
        //given
        when(mockUserRepository.save(any())).thenReturn(user);

        HandlingResult result = RequestFixture.handle(new LoginHandler(mockUserRepository), fixture ->
                fixture.uri("/login")
        );

        assertEquals(HttpResponseStatus.OK, result.getStatus().getNettyStatus());
        assertEquals(LoginResponse.class, result.rendered(DefaultJsonRender.class).getObject().getClass());
    }
}