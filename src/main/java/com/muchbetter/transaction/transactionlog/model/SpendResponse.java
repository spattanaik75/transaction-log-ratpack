package com.muchbetter.transaction.transactionlog.model;

import java.math.BigDecimal;

public record SpendResponse(BigDecimal remainingBalance) {
}
