package com.web_final_task.service;

import com.web_final_task.config.CustomAllureLogFilter;
import com.web_final_task.entity.Payment;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.given;

public class PaymentManagementService extends BaseService {

    public Response createNewPayment(Payment payment, int statusCode) {
        return given()
                .filter(CustomAllureLogFilter.allureLogFilter().withCustomTemplate())
                .spec(requestSpecification)
                .contentType(ContentType.JSON)
                .body(payment)
                .when()
                .post(getServiceUrl())
                .then()
                .statusCode(statusCode)
                .spec(responseSpecification)
                .extract().response();
    }

    public Response deletePaymentByUserId(long userId, int statusCode){
        return  given()
                .filter(CustomAllureLogFilter.allureLogFilter().withCustomTemplate())
                .spec(requestSpecification)
                .when()
                .delete(getServiceUrl() + userId)
                .then()
                .assertThat()
                .spec(responseSpecification)
                .statusCode(statusCode)
                .extract().response();

    }

    public Response updatePayment(Payment payment, int statusCode) {
        return given()
                .filter(CustomAllureLogFilter.allureLogFilter().withCustomTemplate())
                .spec(requestSpecification)
                .body(payment)
                .when()
                .put(getServiceUrl())
                .then()
                .assertThat()
                .spec(responseSpecification)
                .statusCode(statusCode)
                .extract().response();
    }

    public Response updateListOfPaymentsByUser(List<Payment> payment, int statusCode, long userId) {
        return given()
                .filter(CustomAllureLogFilter.allureLogFilter().withCustomTemplate())
                .spec(requestSpecification)
                .body(payment)
                .when()
                .post(getServiceUrl() + userId)
                .then()
                .assertThat()
                .spec(responseSpecification)
                .statusCode(statusCode)
                .extract().response();
    }

    @Override
    protected String getServiceUrl() {
        return "http://localhost:7878/payment/";
    }
}
