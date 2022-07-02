package com.muchbetter.transaction.transactionlog.handler;

import io.netty.handler.codec.http.HttpResponseStatus;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import ratpack.core.handling.Context;

@Slf4j
public class ExceptionHandler {
    public static void render(@NonNull Context ctx, String message) throws Exception {
        log.error(message);
        ctx.getResponse()
                .status(HttpResponseStatus.INTERNAL_SERVER_ERROR.code())
                .send(
                        message != null ? message : HttpResponseStatus.INTERNAL_SERVER_ERROR.reasonPhrase()
                );
    }
}
