package com.web_final_task.annotations.extentions;

import com.web_final_task.annotations.AddUserDetailsForMock;
import com.web_final_task.config.database.IUserRepository;
import com.web_final_task.config.database.UserRepository;
import com.web_final_task.entity.xml.UserDetails;
import com.web_final_task.utility.UserDetailsGenerator;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import static java.util.Optional.ofNullable;

public class CreateUserDetailsForMocksParameterResolver implements ParameterResolver, AfterEachCallback {

    private IUserRepository userRepository = new UserRepository();
    private UserDetails userDetails = null;

    @Override
    public void afterEach(ExtensionContext context) {
        if (ofNullable(userDetails).isPresent()) {
            userRepository.deleteUser(userDetails.getId());
        }
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext,
                                     ExtensionContext extensionContext) throws ParameterResolutionException {
        return extensionContext.getRequiredTestMethod().isAnnotationPresent(AddUserDetailsForMock.class) &&
                parameterContext.getParameter().getType().isAssignableFrom(UserDetails.class);
    }

    @Override
    public UserDetails resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {

        AddUserDetailsForMock addUserDetailsForMock = extensionContext.getRequiredTestMethod().getAnnotation(AddUserDetailsForMock.class);
        if (addUserDetailsForMock == null) {
            throw new ParameterResolutionException("The test method should be annotated with AddUserDetails");
        }

        final long userId = addUserDetailsForMock.value();

        userDetails = UserDetailsGenerator.generateUserDetails(userId);
        userRepository.createUserDetails(userDetails);

        return userDetails;
    }
}
