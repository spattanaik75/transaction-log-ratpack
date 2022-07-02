package com.muchbetter.transaction.transactionlog.model;

import java.math.BigDecimal;

public record BalanceResponse(BigDecimal balance, String currency) {
}
