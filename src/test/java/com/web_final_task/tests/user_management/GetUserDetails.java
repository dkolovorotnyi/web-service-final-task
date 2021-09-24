package com.web_final_task.tests.user_management;

import com.web_final_task.annotations.AddUserDetails;
import com.web_final_task.annotations.Service;
import com.web_final_task.entity.Address;
import com.web_final_task.entity.enams.AddUserDetailsType;
import com.web_final_task.entity.xml.GetUserDetailsRequest;
import com.web_final_task.entity.xml.GetUserDetailsResponse;
import com.web_final_task.entity.xml.UserDetails;
import com.web_final_task.tests.BaseRestTest;
import com.web_final_task.utility.AddressGenerator;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.web_final_task.utility.CommonFaker.getFakerWithDefaultLocale;
import static org.assertj.core.api.Assertions.assertThat;

@Owner("Denis")
@Feature("Receive user details")
@Service(value = "User management")
@DisplayName("Get user details")
class GetUserDetails extends BaseRestTest {

    @Test
    @Issue("CP-17")
    @Story("Receiving user detail")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Receive user details by valid user id")
    @AddUserDetails(AddUserDetailsType.DB)
    void getUserUserDetailByValidUserId(UserDetails userDetails) {
        GetUserDetailsRequest request = new GetUserDetailsRequest();
        request.setUserId(userDetails.getId());

        GetUserDetailsResponse userDetailsResponse = managementService.getUserDetails(request);

        assertThat(userDetailsResponse.getUserDetails()).isEqualTo(userDetails);
    }

    @Test
    @Story("Receiving user detail")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Receive user detail with address only")
    @AddUserDetails(AddUserDetailsType.DB)
    void getUserDetailsWithAddress(UserDetails userDetails) {
        final int addressId = addressRepository.getAll().size() + 1;
        final Address generate = AddressGenerator.generateAddress(userDetails.getId(), addressId);
        addressRepository.save(generate);

        GetUserDetailsRequest request = new GetUserDetailsRequest();
        request.setUserId(userDetails.getId());

        GetUserDetailsResponse getUserDetailsResponse = managementService.getUserDetails(request);

        assertThat(getUserDetailsResponse.getUserDetails().getAddresses().getAddress().size()).isEqualTo(1);
    }

    @Test
    @Story("Receiving user detail")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Receive user details by none existed user id")
    void getUserUserDetailByNoneExistedUserId() {
        final String notFound = "NOT_FOUND";
        final GetUserDetailsRequest request = new GetUserDetailsRequest();
        request.setUserId(getFakerWithDefaultLocale().number().numberBetween(800, 900));

        GetUserDetailsResponse userDetails = managementService.getUserDetails(request);
        assertThat(userDetails.getUserDetails())
                .extracting(UserDetails::getEmail, UserDetails::getLastName, UserDetails::getName)
                .contains(notFound);
    }
}
