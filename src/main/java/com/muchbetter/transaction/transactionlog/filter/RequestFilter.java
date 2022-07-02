package com.muchbetter.transaction.transactionlog.filter;

import ratpack.core.handling.Context;
import ratpack.core.handling.Handler;
import ratpack.core.http.MutableHeaders;

public class RequestFilter implements Handler {
    @Override
    public void handle(Context ctx) throws Exception {
        MutableHeaders headers = ctx.getResponse().getHeaders();
        headers.set("Access-Control-Allow-Origin", "*");
        ctx.next();
    }
}
