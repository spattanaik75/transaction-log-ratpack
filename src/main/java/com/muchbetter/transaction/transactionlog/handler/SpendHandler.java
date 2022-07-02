package com.muchbetter.transaction.transactionlog.handler;

import com.muchbetter.transaction.transactionlog.model.SpendResponse;
import com.muchbetter.transaction.transactionlog.model.Transaction;
import com.muchbetter.transaction.transactionlog.model.User;
import com.muchbetter.transaction.transactionlog.repository.UserRepository;
import ratpack.core.handling.Context;
import ratpack.core.handling.Handler;

import java.math.BigDecimal;

import static ratpack.core.jackson.Jackson.fromJson;
import static ratpack.core.jackson.Jackson.json;

public record SpendHandler(UserRepository userRepository) implements Handler {
    private static final String ERR_CURRENCY_MSG = "transaction currency doesn't match user currency";
    private static final String ERR_FUNDS = "you don't have funds to perform trasaction";

    @Override
    public void handle(Context ctx) throws Exception {
        User loggedInUser = ctx.get(User.class);

        ctx.parse(fromJson(Transaction.class)).then(tx -> {

            BigDecimal remainingBalance = loggedInUser.balance().subtract(tx.amount());

            // exception if currency mismatch
            if (!tx.currency().equals(loggedInUser.currency()))
                ExceptionHandler.render(ctx, ERR_CURRENCY_MSG);
                // exception if funds not available
            else if (remainingBalance.compareTo(BigDecimal.ZERO) < 0)
                ExceptionHandler.render(ctx, ERR_FUNDS);
            else {
                loggedInUser.transactionList().add(tx);

                User updatedUser = new User(
                        loggedInUser.token(),
                        remainingBalance,
                        loggedInUser.currency(),
                        loggedInUser.transactionList()
                );
                userRepository.save(updatedUser);

                ctx.render(
                        json(
                                new SpendResponse(remainingBalance)
                        )
                );
            }

        });
    }
}
