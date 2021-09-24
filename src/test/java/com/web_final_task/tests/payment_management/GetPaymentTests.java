package com.web_final_task.tests.payment_management;

import com.web_final_task.annotations.Service;
import com.web_final_task.entity.Payment;
import com.web_final_task.tests.BaseRestTest;
import com.web_final_task.utility.PaymentGenerator;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static java.lang.String.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

@Owner("Denis")
@Feature("Receiving payments")
@Service(value = "Payment management")
@DisplayName("Get new payments")
class GetPaymentTests extends BaseRestTest {

    @Test
    @Story("Get payment")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("get payment by valid user id")
    void getPaymentByUserId() {
        final Payment generatedPayments = PaymentGenerator.generatePayment(true);
        paymentRepository.save(generatedPayments);

        stubFor(get("/payment/" + generatedPayments.getUserId())
                .willReturn(aResponse().proxiedFrom("http://localhost:8282/")));

        List<Payment> paymentList = paymentService.getPayment(valueOf(generatedPayments.getUserId()), 200)
                .getBody().jsonPath().getList(".", Payment.class);
        assertThat(paymentList.get(0)).isEqualTo(generatedPayments);
    }

    @Test
    @Story("Get payment")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("get payment by invalid user id")
    void getPaymentByUserInvalidId() {
        final String invalidId = "fdfdsfdsf";

        stubFor(get("/payment/" + invalidId)
                .willReturn(aResponse().proxiedFrom("http://localhost:8282/")));

        Response response = paymentService.getPayment(invalidId, 400);
        assertThat(response.getBody().asString()).withFailMessage("Bad Request");
    }


    @Test
    @Story("Get payment")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("get payment by nonexistent user id")
    void getPaymentByNonexistentId() {
        final long nonexistentId = 321;
        final String notFound = "The payment was not fount";
        stubFor(get("/payment/" + nonexistentId)
                .willReturn(aResponse()
                        .withBody(notFound)
                        .withStatus(404)));

        Response response = paymentService.getPayment(valueOf(nonexistentId), 404);
        assertThat(response.getBody().asString()).withFailMessage(notFound);
    }
}
