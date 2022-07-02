package com.muchbetter.transaction.transactionlog.model;

import lombok.NonNull;

import java.math.BigDecimal;
import java.util.List;

public record User(@NonNull String token, @NonNull BigDecimal balance,
                   @NonNull String currency,
                   List<Transaction> transactionList) {
//    As it's not specified what's the relation between a user and a token is. And there is no token expiry at the moment,
//    lets assume it's all in memory and one user has one token in his lifetime.
}
