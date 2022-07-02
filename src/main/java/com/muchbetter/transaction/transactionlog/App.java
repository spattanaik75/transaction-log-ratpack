package com.muchbetter.transaction.transactionlog;

import com.muchbetter.transaction.transactionlog.handler.*;
import com.muchbetter.transaction.transactionlog.repository.UserRepository;
import com.muchbetter.transaction.transactionlog.repository.impl.UserRepositoryInMemory;
import com.muchbetter.transaction.transactionlog.repository.impl.UserRepositoryRedis;
import lombok.extern.slf4j.Slf4j;
import ratpack.core.server.RatpackServer;

@Slf4j
public class App {

    public static void main(String[] args) throws Exception {
        final UserRepository userRepository = getRepository();
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

    private static UserRepository getRepository() {
        if (System.getenv("REDIS_URL") != null) {
            log.info("Using REDIS db as backend");
            return new UserRepositoryRedis();
        } else {
            // DEFAULT
            log.warn("REDIS_URL not found. Using IN_MEMORY db by default");
            return new UserRepositoryInMemory();
        }

    }
}


