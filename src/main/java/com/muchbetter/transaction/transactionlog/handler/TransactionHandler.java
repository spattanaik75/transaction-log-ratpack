package com.muchbetter.transaction.transactionlog.handler;

import com.muchbetter.transaction.transactionlog.model.Transaction;
import com.muchbetter.transaction.transactionlog.model.User;
import ratpack.core.handling.Context;
import ratpack.core.handling.Handler;

import java.util.List;

import static ratpack.core.jackson.Jackson.json;

public record TransactionHandler() implements Handler {

    @Override
    public void handle(Context ctx) throws Exception {
        User registryUser = ctx.get(User.class);
        List<Transaction> transactionList = registryUser.transactionList();

        ctx.render(
                json(transactionList)
        );
    }
}
