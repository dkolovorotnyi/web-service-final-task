package com.web_final_task.tests.payment_management;

import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
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
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.File;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.containing;
import static com.github.tomakehurst.wiremock.client.WireMock.put;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static java.lang.String.valueOf;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Owner("Denis")
@Story("Payment")
@Feature("Update existing payment")
@Service(value = "Payment management")
@DisplayName("Update existing payments")
@ExtendWith({WireMockServerExtension.class})
class UpdateExistingPayment extends BaseRestTest {

    @Test
    @Issue("CP-12")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Update payment with valid data")
    @SneakyThrows
    void updatePayments() {
        final Payment generatedPayments = PaymentGenerator.generatePayment(true);
        objectMapper.writeValue(new File(GENERATED_JSON_PATH +
                generatedPayments.getUserId().toString() + ".json"), generatedPayments);

        ResponseDefinitionBuilder updatedPaymentResponse = new ResponseDefinitionBuilder();
        updatedPaymentResponse.withStatus(200);
        updatedPaymentResponse.withBodyFile(PATH_TO_PAYMENT_JSONS + generatedPayments.getUserId().toString() + ".json");
        updatedPaymentResponse.withHeader("Content-Type", "application/json");

        stubFor(put("/payment/").willReturn(updatedPaymentResponse));

        Payment updatePayment = paymentService.updatePayment(generatedPayments, 200).as(Payment.class);
        assertThat(updatePayment).isEqualTo(generatedPayments);
    }

    @Test
    @Issue("CP-13")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Update payment with invalid data")
    @SneakyThrows
    void updatePaymentWithInvalidData() {
        final Payment generatedPayments = PaymentGenerator.generatePayment(true);
        paymentRepository.save(generatedPayments);
        generatedPayments.setId(-45);

        objectMapper.writeValue(new File(GENERATED_JSON_PATH +
                generatedPayments.getUserId().toString() + ".json"), generatedPayments);

        stubFor(put("/payment/")
                .withRequestBody(containing(valueOf(generatedPayments.getId())))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(400)
                        .withBody("Invalid id")));

        final Response response = paymentService.updatePayment(generatedPayments, 400);
        assertThat(response.getBody().asString()).isEqualTo("Invalid id");
    }

    @Test
    @Issue("CP-14")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Update payment with none existed id")
    @SneakyThrows
    void updatePaymentWithNoneExistedId() {
        final String responseMessage = "Payment was not found";
        final Payment payment = PaymentGenerator.generatePayment(true);
        paymentRepository.save(payment);
        payment.setId(90778);

        objectMapper.writeValue(new File(GENERATED_JSON_PATH + payment.getUserId().toString() + ".json"), payment);

        stubFor(put("/payment/")
                .withRequestBody(containing(valueOf(payment.getId())))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(404)
                        .withBody(responseMessage)));

        Response response = paymentService.updatePayment(payment, 404);
        assertThat(response.getBody().asString()).isEqualTo(responseMessage);
    }
}
