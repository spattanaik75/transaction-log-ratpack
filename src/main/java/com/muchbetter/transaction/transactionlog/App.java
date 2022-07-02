package com.muchbetter.transaction.transactionlog;

import com.muchbetter.transaction.transactionlog.handler.*;
import com.muchbetter.transaction.transactionlog.repository.UserRepository;
import com.muchbetter.transaction.transactionlog.repository.impl.UserRepositoryInMemory;
import ratpack.core.server.RatpackServer;


public class App {

    public static void main(String[] args) throws Exception {
        UserRepository userRepository = new UserRepositoryInMemory();

        RatpackServer.start(server -> server
//                .registry(
//                        Guice.registry(bindingsSpec -> bindingsSpec
//                                .bind(ServerErrorHandler.class, InternalServerException.class)
//                        )
//                )
                .handlers(chain -> chain

                        // health check endpoint
                        .get("health", new HealthHandler())

                        // get auth token
                        .post("login", new LoginHandler(userRepository))

                        // verify auth token for subsequent calls
                        .all(new AuthHandler(userRepository))

                        // get balance for logged in user
                        .get("balance", new BalanceHandler())

                        // get all transactions for logged in user
                        .get("transactions", new TransactionHandler())

                        // spend all your money here
                        .post("spend", new SpendHandler(userRepository))
                ));
    }
}


