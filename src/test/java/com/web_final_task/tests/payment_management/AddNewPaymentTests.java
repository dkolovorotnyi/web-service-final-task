package com.web_final_task.tests.payment_management;

import com.web_final_task.annotations.Service;
import com.web_final_task.entity.Payment;
import com.web_final_task.tests.BaseRestTest;
import com.web_final_task.utility.PaymentGenerator;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.containing;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Owner("Denis")
@Story("Payment")
@Feature("add new payment")
@Service(value = "Payment management")
@DisplayName("Add new payments")
class AddNewPaymentTests extends BaseRestTest {

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Description("Create a new payment")
    void shouldAddNewPayment() {
        final Payment generatedPayment = PaymentGenerator.generatePayment(true);
        stubFor(post("/payment/")
                .withRequestBody(containing(generatedPayment.getUserId().toString()))
                .willReturn(aResponse().proxiedFrom("http://localhost:8282/")));

        Payment newPayment = paymentService.createNewPayment(generatedPayment, 201).as(Payment.class);

        assertThat(newPayment).isNotNull();
        assertThat(newPayment).isEqualTo(generatedPayment);
    }

    @Test
    @Issue("CP-1")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Create a new unverified payment")
    void shouldNotAddNewPaymentUnverifiedUser() {
        final Payment generatedPayment = PaymentGenerator.generatePayment(false);
        stubFor(post("/payment/")
                .withRequestBody(containing(generatedPayment.getUserId().toString()))
                .willReturn(aResponse().proxiedFrom("http://localhost:8282/")));

        final Payment newPayment = paymentService.createNewPayment(generatedPayment, 201).as(Payment.class);

        assertThat(newPayment).isNotNull();
        assertThat(newPayment).isEqualTo(generatedPayment);
    }

    @Test
    @Issue("CP-10")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Create a new payment")
    void shouldAddNewPaymentIncorrectCvv() {
        final String invalidCvv = "$##@#";
        final String invalidInput = "Invalid input";
        Payment generatedPayment = PaymentGenerator.generatePaymentWithCustomCVV(invalidCvv);

        stubFor(post("/payment/")
                .withRequestBody(containing(invalidCvv))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(405)
                        .withBody(invalidInput)));

        Response response = paymentService.createNewPayment(generatedPayment, 405);
        assertThat(response.getBody().asString()).isEqualTo(invalidInput);
    }
}
