import com.fasterxml.jackson.databind.ObjectMapper;
import com.muchbetter.transaction.transactionlog.App;
import com.muchbetter.transaction.transactionlog.model.Transaction;
import io.netty.handler.codec.http.HttpResponseStatus;
import org.junit.jupiter.api.Test;
import ratpack.core.http.HttpMethod;
import ratpack.core.http.client.ReceivedResponse;
import ratpack.test.ApplicationUnderTest;
import ratpack.test.MainClassApplicationUnderTest;
import ratpack.test.http.TestHttpClient;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;


class AppTest {

    private static MainClassApplicationUnderTest aut = new MainClassApplicationUnderTest(App.class);

    @Test
    void userIsCreatedAndReturnsToken() {
        ReceivedResponse response = aut.getHttpClient().post("login");
        assertEquals(HttpResponseStatus.OK, response.getStatus().getNettyStatus());
    }

    @Test
    void sendTransactionForValidTokenReturnsCreated() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        // Create new user
        TestHttpClient httpClient = aut.getHttpClient();
        ReceivedResponse loginResponse = httpClient.post("login");
        String auth = loginResponse.getHeaders().get("Authorization");

        // New user spends something
        Transaction transaction = new Transaction(LocalDateTime.now().toString(),
                "Coke",
                new BigDecimal("10.00"),
                "ZAR");

        ReceivedResponse spendResponse = httpClient.request("spend", req -> {
            req
                    .headers(mutableHeaders -> mutableHeaders
                            .set("Authorization", auth)
                            .set("Content-Type", "application/json"))
                    .method(HttpMethod.POST)
                    .body(body -> {
                        body.text(objectMapper.writeValueAsString(transaction));
                    });
        });

        assertEquals(HttpResponseStatus.OK, spendResponse.getStatus().getNettyStatus());
    }
}