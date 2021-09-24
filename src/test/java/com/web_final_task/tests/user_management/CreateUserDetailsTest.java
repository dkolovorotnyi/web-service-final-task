package com.web_final_task.tests.user_management;

import com.web_final_task.annotations.Service;
import com.web_final_task.entity.xml.CreateUserRequest;
import com.web_final_task.entity.xml.CreateUserResponse;
import com.web_final_task.tests.BaseRestTest;
import com.web_final_task.utility.UserDetailsGenerator;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Owner("Denis")
@Feature("Create user details")
@Service(value = "User management e2e")
@DisplayName("Create user details")
class CreateUserDetailsTest extends BaseRestTest {

    @Test
    @Issue("CP-2")
    @Story("Creating user details")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Create user details without payments and addresses")
    void createUserDetails() {
        final CreateUserRequest createUserRequest = UserDetailsGenerator.generateWithoutPaymentsAndAddresses();
        CreateUserResponse user = managementService.createUserDetails(createUserRequest);

        assertThat(user.getUserDetails().getEmail()).isEqualTo(createUserRequest.getEmail());
        assertThat(user.getUserDetails().getName()).isEqualTo(createUserRequest.getName());
        assertThat(user.getUserDetails().getBirthday()).isEqualTo(createUserRequest.getBirthday());
        assertThat(user.getUserDetails().getAddresses().getAddress()).isEmpty();
        assertThat(user.getUserDetails().getPayments().getPayment()).isEmpty();
    }
}
