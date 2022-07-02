package com.muchbetter.transaction.transactionlog.handler;

import com.muchbetter.transaction.transactionlog.model.User;
import com.muchbetter.transaction.transactionlog.repository.UserRepository;
import io.netty.handler.codec.http.HttpResponseStatus;
import ratpack.core.handling.Context;
import ratpack.core.handling.Handler;
import ratpack.exec.Blocking;
import ratpack.exec.registry.Registry;

import java.util.Optional;

public record AuthHandler(UserRepository userRepository) implements Handler {
    @Override
    public void handle(Context ctx) throws Exception {
        Optional<String> token = ctx.header("Authorization");
        if (token.isPresent()) {
            // https://ratpack.io/manual/current/async.html#performing_blocking_operations_eg_io
            // https://ratpack.io/manual/current/all.html#6%20Context
            Blocking
                    // tokens are unique per user. so we can pre render the user and pass it for downstream handles
                    // get user by token
                    .get(() -> userRepository.findByToken(token.get()))
                    // if user exists, add it to registry and pass to downstream
                    .then(user -> {
                        if (user != null) {
                            ctx.next(Registry.single(User.class, user));
                        } else {
                            ctx.getResponse()
                                    .status(HttpResponseStatus.FORBIDDEN.code())
                                    .send(HttpResponseStatus.FORBIDDEN.reasonPhrase());
                        }
                    });

        } else ctx.getResponse()
                .status(HttpResponseStatus.UNAUTHORIZED.code())
                .send(HttpResponseStatus.UNAUTHORIZED.reasonPhrase());
        ;
    }
}
