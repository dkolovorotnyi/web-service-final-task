package com.web_final_task.tests.user_management;

import com.web_final_task.annotations.AddUserDetails;
import com.web_final_task.annotations.Service;
import com.web_final_task.entity.enams.AddUserDetailsType;
import com.web_final_task.entity.xml.UpdateUserRequest;
import com.web_final_task.entity.xml.UpdateUserResponse;
import com.web_final_task.entity.xml.UserDetails;
import com.web_final_task.tests.BaseRestTest;
import com.web_final_task.utility.UserDetailsGenerator;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Owner("Denis")
@Feature("Update user details")
@Service(value = "User management")
class UpdateUserDetails extends BaseRestTest {

    @Test
    @Issue("CP-19")
    @Story("Updating user details")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Update user details by valid user id")
    @AddUserDetails(AddUserDetailsType.DB)
    void updateUserDetailsWithValidData(UserDetails userDetails) {
        final UserDetails newUserDetails = UserDetailsGenerator.generateUserDetails(userDetails.getId());
        final UpdateUserRequest userRequest = new UpdateUserRequest();
        userRequest.setUserDetails(newUserDetails);

        UpdateUserResponse updateUserResponse = managementService.updateUserDetails(userRequest);
        assertThat(updateUserResponse.getUserDetails()).isEqualTo(userDetails);
    }
}
