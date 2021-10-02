package com.web_final_task.tests.gateway_mocks;

import com.web_final_task.annotations.Service;
import com.web_final_task.entity.Payment;
import com.web_final_task.tests.BaseRestTest;
import com.web_final_task.utility.PaymentGenerator;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.containing;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.put;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static java.lang.String.valueOf;

@Service("Gateway")
@DisplayName("Gateway mock tests")
class GatewayTest extends BaseRestTest {

    @Test
    @Issue("CP-22")
    @Story("Gateway is not available")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Update user details when gateway is not reachable")
    void updateUserDetailsWhenGatewayIsNotReachable() {
        final String internalServerError = "Internal Server error";
        final Payment generatedPayments = PaymentGenerator.generatePayment(true);
        paymentRepository.save(generatedPayments);
        generatedPayments.setExpiryMonth(7);
        generatedPayments.setUserId(72L);
        stubFor(put("/payment/")
                .withRequestBody(containing(valueOf(generatedPayments.getId())))
                .willReturn(aResponse().proxiedFrom("http://localhost:8282/")));

        stubFor(post("/card/verify")
                .withRequestBody(containing(generatedPayments.getCardNumber()))
                .willReturn(aResponse()
                        .withStatus(500)
                        .withHeader("Content-Type", "text/plain")
                        .withStatusMessage(internalServerError)));

        Payment updatePayment = paymentService.updatePayment(generatedPayments, 200).as(Payment.class);
        AssertionsForClassTypes.assertThat(updatePayment).isEqualTo(generatedPayments);
    }


    @Test
    @Issue("CP-22")
    @Story("Gateway is not available")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Create user details when gateway is not reachable")
    void createUserDetailsWhenGatewayIsNotReachable() {
        final String internalServerError = "Internal Server error";
        final Payment generatedPayments = PaymentGenerator.generatePayment(true);
        stubFor(post("/payment/")
                .withRequestBody(containing(valueOf(generatedPayments.getId())))
                .willReturn(aResponse().proxiedFrom("http://localhost:8282/")));

        stubFor(post("/card/verify")
                .withRequestBody(containing(generatedPayments.getCardNumber()))
                .willReturn(aResponse()
                        .withStatus(500)
                        .withHeader("Content-Type", "text/plain")
                        .withStatusMessage(internalServerError)));

        Payment updatePayment = paymentService.createNewPayment(generatedPayments, 200).as(Payment.class);
        AssertionsForClassTypes.assertThat(updatePayment).isEqualTo(generatedPayments);
    }
}
