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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.delete;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Owner("Denis")
@Story("Payment")
@Feature("Deletes all payments for user by provided ID")
@Service(value = "Payment management")
@DisplayName("Delete payments")
class DeletePaymentTest extends BaseRestTest {

    @Test
    @Issue("CP-12")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Delete all payments by valid user id")
    void deleteAllPaymentsByUserId() {
        final String expectedResult = "The Payment removed";
        final Payment generatedPayments = PaymentGenerator.generatePayment(false);
        paymentRepository.save(generatedPayments);
        stubFor(delete("/payment/" + generatedPayments.getId())
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody(expectedResult)));

        final Response response = paymentService.deletePaymentByUserId(generatedPayments.getId(), 200);
        assertThat(response.getBody().asString()).isEqualTo(expectedResult);
    }

    @Test
    @Issue("CP-11")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Delete all Payments by invalid user id")
    void deleteAllPaymentsByInvalidUserId() {
        final long invalidUserId = -2;
        final String validationException = "Validation exception";

        stubFor(delete("/payment/" + invalidUserId)
                .willReturn(aResponse()
                        .withStatus(400)
                        .withBody(validationException)));

        final Response response = paymentService.deletePaymentByUserId(invalidUserId, 400);
        assertThat(response.getBody().asString()).isEqualTo(validationException);
    }

    @Test
    @Issue("CP-12")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Delete all Payments by invalid user id")
    void deleteAllPaymentsByNoneExistUserId() {
        final long noneExistUserId = 313213;
        final String paymentNotFound = "Not Found";
        stubFor(delete("/payment/" + noneExistUserId)
                .willReturn(aResponse()
                        .withStatus(404)
                        .withBody(paymentNotFound)));

        final Response response = paymentService.deletePaymentByUserId(noneExistUserId, 404);
        assertThat(response.getBody().asString()).isEqualTo(paymentNotFound);
    }
}
