package com.muchbetter.transaction.transactionlog.handler;

import ratpack.core.handling.Context;
import ratpack.core.handling.Handler;

public class HealthHandler implements Handler {
    @Override
    public void handle(Context ctx) throws Exception {
        ctx.render("UP");
    }
}
