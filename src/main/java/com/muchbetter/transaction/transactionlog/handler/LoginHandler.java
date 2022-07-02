package com.muchbetter.transaction.transactionlog.handler;

import com.muchbetter.transaction.transactionlog.model.LoginResponse;
import com.muchbetter.transaction.transactionlog.model.User;
import com.muchbetter.transaction.transactionlog.repository.UserRepository;
import com.muchbetter.transaction.transactionlog.utility.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import ratpack.core.handling.Context;
import ratpack.core.handling.Handler;

import java.math.BigDecimal;
import java.util.ArrayList;

import static ratpack.core.jackson.Jackson.json;

@Slf4j
public record LoginHandler(UserRepository userRepository) implements Handler {

    @Override
    public void handle(Context ctx) throws Exception {
        String newToken = TokenUtil.getToken();
        // Business Logic: Use Presets to generate a user
        User newUser = new User(newToken, new BigDecimal("10000.00"), "ZAR", new ArrayList<>());
        // save user to db
        userRepository.save(newUser);
        // return tokenReponse
        LoginResponse response = new LoginResponse(newToken);

        log.info(userRepository.findAll().toString());

        // use jackson to modify response
        ctx.render(json(response));
    }
}
