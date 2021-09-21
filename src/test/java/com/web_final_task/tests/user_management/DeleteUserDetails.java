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
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;


@Owner("Denis")
@Feature("Removing User Details ")
@Service(value = "User management")
class DeleteUserDetails extends BaseRestTest {

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Delete user details by valid user id")
    @Story("Delete user details")
    @AddUserDetails(AddUserDetailsType.DB)
    void deleteUserDetailsByValidId(UserDetails userDetails) {
        DeleteUserRequest request = new DeleteUserRequest();
        request.setUserId(userDetails.getId());

        DeleteUserResponse deleteUserResponse = managementService.deleteUserDetails(request);

        assertThat(deleteUserResponse.getMessage()).isEqualTo(null);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Delete all Address by valid user id")
    @AddUserDetails(AddUserDetailsType.DB)
    void deleteUserDetailsWithAddressData(UserDetails userDetails) {
        final int addressId = addressRepository.getAll().size() + 1;
        final Address generatedAddress = AddressGenerator.generate(addressId, userDetails.getId());
        final Payment generatedPayment = PaymentGenerator.generatePaymentWithCustomUserId(userDetails.getId());

        addressRepository.save(generatedAddress);
        paymentRepository.save(generatedPayment);

        DeleteUserRequest request = new DeleteUserRequest();
        request.setUserId(userDetails.getId());

        DeleteUserResponse deleteUserResponse = managementService.deleteUserDetails(request);

        assertThat(deleteUserResponse.getMessage()).isEqualTo(null);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Delete all Payments by nonexistent user id")
    void deleteUserDetailsByNonexistentId() {
        DeleteUserRequest deleteUserRequest = new DeleteUserRequest();
        deleteUserRequest.setUserId(99999999);

        assertThatThrownBy(() ->
                managementService.deleteUserDetails(deleteUserRequest))
                .hasMessageContaining("User with given id does not exist!");
    }
}


