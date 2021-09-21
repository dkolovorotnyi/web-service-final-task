package com.web_final_task.tests.payment_management;

import com.web_final_task.annotations.Service;
import com.web_final_task.annotations.extentions.WireMockServerExtension;
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
import org.junit.jupiter.api.extension.ExtendWith;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.delete;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Owner("Denis")
@Story("Payment")
@Feature("Deletes all payments for user by provided ID")
@Service(value = "Payment management")
@ExtendWith({WireMockServerExtension.class})
@DisplayName("Delete payments")
class DeletePayment extends BaseRestTest {

    @Test
    @Issue("CP-12")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Delete all payments by valid user id")
    void deleteAllPaymentsByUserId() {
        final Payment generatedPayments = PaymentGenerator.generatePayment(true);
        paymentRepository.save(generatedPayments);

        stubFor(delete("/payment/" + generatedPayments.getId())
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody("the Payment removed")));

        final Response response = paymentService.deletePaymentByUserId(generatedPayments.getId(), 200);
        assertThat(response.getBody().asString()).isEqualTo("the Payment removed");
    }

    @Test
    @Issue("CP-11")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Delete all Payments by invalid user id")
    void deleteAllPaymentsByInvalidUserId() {
        final long invalidUserId = -2;
        stubFor(delete("/payment/" + invalidUserId)
                .willReturn(aResponse()
                        .withStatus(400)
                        .withBody("Validation exception")));

        final Response response = paymentService.deletePaymentByUserId(invalidUserId, 400);
        assertThat(response.getBody().asString()).isEqualTo("Validation exception");
    }

    @Test
    @Issue("CP-12")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Delete all Payments by invalid user id")
    void deleteAllPaymentsByNoneExistUserId() {
        final long noneExistUserId = 313213;
        stubFor(delete("/payment/" + noneExistUserId)
                .willReturn(aResponse()
                        .withStatus(404)
                        .withBody("Not Found")));

        final Response response = paymentService.deletePaymentByUserId(noneExistUserId, 404);
        assertThat(response.getBody().asString()).isEqualTo("Not Found");
    }
}
