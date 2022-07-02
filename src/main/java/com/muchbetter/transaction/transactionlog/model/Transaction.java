package com.muchbetter.transaction.transactionlog.model;

import lombok.NonNull;

import java.io.Serializable;
import java.math.BigDecimal;

public record Transaction(@NonNull String date, @NonNull String description,
                          @NonNull BigDecimal amount,
                          @NonNull String currency) implements Serializable {
}
