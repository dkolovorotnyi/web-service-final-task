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
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.equalToJson;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.web_final_task.utility.CommonFaker.getFakerWithDefaultLocale;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Owner("Denis")
@Story("Payment")
@Feature("Updates all payments for a user with provided ID")
@Service(value = "Payment management")
@DisplayName("Update existing payments by user id")
class UpdatesPaymentsByUserId extends BaseRestTest {

    @Test
    @Issue("CP-14")
    @Severity(SeverityLevel.BLOCKER)
    @SneakyThrows
    @Description(value = "update all payments by valid user id")
    void updateAllPaymentsByValidUserId() {
        final long userId = getFakerWithDefaultLocale().number().numberBetween(1, 999);
        List<Payment> generatedPayments = PaymentGenerator.generateListOfPayment(2, userId);
        generatedPayments.forEach(payment -> paymentRepository.save(payment));
        List<Payment> updatedListOfPayments = updatedListOfPayments(userId, generatedPayments);

        objectMapper.writeValue(new File(GENERATED_JSON_PATH + userId + ".json"), updatedListOfPayments);

        final ResponseDefinitionBuilder updatedPaymentResponse = new ResponseDefinitionBuilder();
        updatedPaymentResponse.withStatus(200);
        updatedPaymentResponse.withBodyFile(PATH_TO_PAYMENT_JSONS + userId + ".json");
        updatedPaymentResponse.withHeader("Content-Type", "application/json");

        stubFor(post("/payment/" + userId).willReturn(updatedPaymentResponse));


        final List<Payment> updateListOfPayments = paymentService.updateListOfPaymentsByUser(updatedListOfPayments, 200, userId)
                .jsonPath().getList(".", Payment.class);

        assertThat(updateListOfPayments.size() == updatedListOfPayments.size() && updateListOfPayments.containsAll(updatedListOfPayments));
    }

    @Test
    @Issue("CP-15")
    @Severity(SeverityLevel.BLOCKER)
    @SneakyThrows
    @Description(value = "update all payments by invalid user id")
    void updateAllPaymentsWithEmptyListOfPayments() {
        final String invalidInput = "Invalid input";
        final long userId = getFakerWithDefaultLocale().number().numberBetween(1, 999);
        List<Payment> generatedPayments = PaymentGenerator.generateListOfPayment(4, userId);
        generatedPayments.forEach(payment -> paymentRepository.save(payment));
        generatedPayments.clear();

        objectMapper.writeValue(new File(GENERATED_JSON_PATH + userId + ".json"), generatedPayments);

        ResponseDefinitionBuilder updatedPaymentResponse = new ResponseDefinitionBuilder();
        updatedPaymentResponse.withStatus(405);
        updatedPaymentResponse.withBody(invalidInput);
        updatedPaymentResponse.withHeader("Content-Type", "application/json");

        stubFor(post("/payment/" + userId)
                .withRequestBody(equalToJson("[]"))
                .willReturn(updatedPaymentResponse));

        Response response = paymentService.updateListOfPaymentsByUser(generatedPayments, 405, userId);

        assertThat(response.getBody().asString()).isEqualTo(invalidInput);
    }

    private List<Payment> updatedListOfPayments(long userId, List<Payment> generatedPayments) {
        generatedPayments.forEach(payment -> {
            final Payment newPayment = PaymentGenerator.generatePaymentWithCustomUserId(userId);
            payment.setCvv(newPayment.getCvv());
            payment.setCardHolder(newPayment.getCardHolder());
            payment.setCardNumber(newPayment.getCardNumber());
            payment.setExpiryMonth(newPayment.getExpiryMonth());
            payment.setExpiryYear(newPayment.getExpiryMonth());
        });

        return generatedPayments;
    }
}
