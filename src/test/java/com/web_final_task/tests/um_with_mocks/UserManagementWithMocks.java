package com.web_final_task.tests.um_with_mocks;

import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.web_final_task.annotations.AddUserDetails;
import com.web_final_task.annotations.AddUserDetailsForMock;
import com.web_final_task.annotations.Service;
import com.web_final_task.annotations.extentions.WireMockServerExtension;
import com.web_final_task.entity.enams.AddUserDetailsType;
import com.web_final_task.entity.xml.DeleteUserRequest;
import com.web_final_task.entity.xml.GetUserDetailsRequest;
import com.web_final_task.entity.xml.GetUserDetailsResponse;
import com.web_final_task.entity.xml.UserDetails;
import com.web_final_task.tests.BaseRestTest;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;


@Owner("Denis")
@Service(value = "User management")
@DisplayName("User Management with mocks")
@ExtendWith({WireMockServerExtension.class})
class UserManagementWithMocks extends BaseRestTest {

    @Test
    @Story("User management with an payment and an address")
    @Severity(SeverityLevel.CRITICAL)
    @AddUserDetailsForMock(value = 900)
    @Description("Receive user details with an payment and an address")
    void getUserDetailsWithPaymentAndAddress(UserDetails userDetails) {
        ResponseDefinitionBuilder addressBuilder = new ResponseDefinitionBuilder();
        addressBuilder.withStatus(200);
        addressBuilder.withBodyFile("/json/validAddress1.json");
        addressBuilder.withHeader("Content-Type", "application/json");


        ResponseDefinitionBuilder paymentBuilder = new ResponseDefinitionBuilder();
        paymentBuilder.withStatus(200);
        paymentBuilder.withBodyFile("/json/validPayment.json");
        paymentBuilder.withHeader("Content-Type", "application/json");

        stubFor(get("/address/" + userDetails.getId()).willReturn(addressBuilder));
        stubFor(get("/payment/" + userDetails.getId()).willReturn(paymentBuilder));


        GetUserDetailsRequest request = new GetUserDetailsRequest();
        request.setUserId(userDetails.getId());
        GetUserDetailsResponse userDetailsResponse = managementService.getUserDetails(request);

        assertThat(userDetailsResponse.getUserDetails().getAddresses().getAddress()).size().isEqualTo(1);
        assertThat(userDetailsResponse.getUserDetails().getPayments().getPayment()).size().isEqualTo(1);
    }

    @Test
    @Story("User management with an payment and an address")
    @Severity(SeverityLevel.NORMAL)
    @AddUserDetailsForMock(value = 800)
    @Description("Receive user details when the payment service is not reachable")
    void getUserDetailsWhenPaymentsIsNotReachable(UserDetails userDetails) {
        ResponseDefinitionBuilder addressBuilder = new ResponseDefinitionBuilder();
        addressBuilder.withStatus(200);
        addressBuilder.withBodyFile("/json/validAddress2.json");
        addressBuilder.withHeader("Content-Type", "application/json");

        stubFor(get("/address/" + userDetails.getId()).willReturn(addressBuilder));
        stubFor(get("/payment/" + userDetails.getId()).willReturn(aResponse()
                .withStatus(500)
                .withBody("Internal Server Error")));

        GetUserDetailsRequest request = new GetUserDetailsRequest();
        request.setUserId(userDetails.getId());

        GetUserDetailsResponse userDetailsResponse = managementService.getUserDetails(request);

        assertThat(userDetailsResponse.getUserDetails().getAddresses().getAddress()).size().isEqualTo(1);
        assertThat(userDetailsResponse.getUserDetails().getPayments().getPayment().size()).isEqualTo(0);
    }

    @Test
    @Story("User management with an payment and an address")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Delete user details when the address management server is not reachable")
    @AddUserDetails(AddUserDetailsType.DB)
    void removeUserDetailsWhenAddressManagementIsNotReachable(UserDetails userDetails) {
        stubFor(get("/address/" + userDetails.getId()).willReturn(aResponse()
                .withStatus(500)
                .withBody("Internal Server Error")));

        DeleteUserRequest request = new DeleteUserRequest();
        request.setUserId(userDetails.getId());

        assertThatThrownBy(() -> managementService.deleteUserDetails(request))
                .hasMessageContaining("Can not delete user's payments and/or addresses");
    }
}
