package com.web_final_task.tests.user_management;

import com.web_final_task.annotations.AddUserDetails;
import com.web_final_task.annotations.Service;
import com.web_final_task.entity.Address;
import com.web_final_task.entity.Payment;
import com.web_final_task.entity.enams.AddUserDetailsType;
import com.web_final_task.entity.xml.DeleteUserRequest;
import com.web_final_task.entity.xml.DeleteUserResponse;
import com.web_final_task.entity.xml.UserDetails;
import com.web_final_task.tests.BaseRestTest;
import com.web_final_task.utility.AddressGenerator;
import com.web_final_task.utility.PaymentGenerator;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@Owner("Denis")
@Feature("Remove user details ")
@Service(value = "User management")
@DisplayName("Delete user details e2e")
class DeleteUserDetailsTest extends BaseRestTest {

    @Test
    @Issue("CP-21")
    @Story("Removing user details")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Delete user details by valid user id")
    @AddUserDetails(AddUserDetailsType.DB)
    void deleteUserDetailsByValidId(UserDetails userDetails) {
        DeleteUserRequest request = new DeleteUserRequest();
        request.setUserId(userDetails.getId());
        stubFor(get("/payment/" + userDetails.getId())
                .willReturn(aResponse().proxiedFrom("http://localhost:8282/")));

        stubFor(get("/address/" + userDetails.getId())
                .willReturn(aResponse().proxiedFrom("http://localhost:8181/")));

        DeleteUserResponse deleteUserResponse = managementService.deleteUserDetails(request);
        assertThat(deleteUserResponse.getMessage()).isNull();
    }

    @Test
    @Issue("CP-22")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Removing all Address by valid user id")
    @AddUserDetails(AddUserDetailsType.DB)
    void deleteUserDetailsWithAddressData(UserDetails userDetails) {
        final int addressId = addressRepository.getAll().size() + 1;
        final Address generatedAddress = AddressGenerator.generateAddress(addressId, userDetails.getId());
        final Payment generatedPayment = PaymentGenerator.generatePaymentWithCustomUserId(userDetails.getId());
        addressRepository.save(generatedAddress);
        paymentRepository.save(generatedPayment);
        stubFor(get("/payment/" + userDetails.getId())
                .willReturn(aResponse().proxiedFrom("http://localhost:8282/")));

        stubFor(get("/address/" + userDetails.getId())
                .willReturn(aResponse().proxiedFrom("http://localhost:8181/")));

        DeleteUserRequest request = new DeleteUserRequest();
        request.setUserId(userDetails.getId());

        DeleteUserResponse deleteUserResponse = managementService.deleteUserDetails(request);
        assertThat(deleteUserResponse.getMessage()).isNull();
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Removing all Payments by nonexistent user id")
    void deleteUserDetailsByNonexistentId() {
        final int userId = 99999999;
        DeleteUserRequest deleteUserRequest = new DeleteUserRequest();
        deleteUserRequest.setUserId(userId);

        assertThatThrownBy(() ->
                managementService.deleteUserDetails(deleteUserRequest))
                .hasMessageContaining("User with given id does not exist!");
    }
}


