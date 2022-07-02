package com.muchbetter.transaction.transactionlog.handler;

import com.muchbetter.transaction.transactionlog.model.BalanceResponse;
import com.muchbetter.transaction.transactionlog.model.User;
import ratpack.core.handling.Context;
import ratpack.core.handling.Handler;

import static ratpack.core.jackson.Jackson.json;

public record BalanceHandler() implements Handler {

    @Override
    public void handle(Context ctx) throws Exception {
        // find preloaded user from registry
        User registryUser = ctx.get(User.class);
        BalanceResponse response = new BalanceResponse(registryUser.balance(), registryUser.currency());
        // render json response
        ctx.render(
                json(response)
        );
    }
}
