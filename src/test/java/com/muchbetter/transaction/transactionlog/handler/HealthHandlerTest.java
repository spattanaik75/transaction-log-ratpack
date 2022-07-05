package com.muchbetter.transaction.transactionlog.handler;

import org.junit.jupiter.api.Test;
import ratpack.test.handling.HandlingResult;
import ratpack.test.handling.RequestFixture;

import static org.junit.jupiter.api.Assertions.*;

class HealthHandlerTest {

    @Test
    void handle() throws Exception {
        HandlingResult result = RequestFixture.handle(new HealthHandler(), fixture ->
                fixture.uri("/health")
        );

        assertEquals(200, result.getStatus().getCode());
        assertEquals("UP", result.rendered(String.class));
    }
}