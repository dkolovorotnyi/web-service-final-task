package com.web_final_task.annotations.extentions;

import com.web_final_task.annotations.AddUserDetails;
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

public class AddUserDetailsParameterResolver implements ParameterResolver, AfterEachCallback {

    private IUserRepository userRepository = new UserRepository();
    private UserDetails userDetails = null;

    @Override
    public boolean supportsParameter(ParameterContext parameterContext,
                                     ExtensionContext extensionContext) throws ParameterResolutionException {
        return extensionContext.getRequiredTestMethod().isAnnotationPresent(AddUserDetails.class) &&
                parameterContext.getParameter().getType().isAssignableFrom(UserDetails.class);
    }

    @Override
    public UserDetails resolveParameter(ParameterContext parameterContext,
                                        ExtensionContext extensionContext) throws ParameterResolutionException {
        AddUserDetails addUserDetails = extensionContext.getRequiredTestMethod().getAnnotation(AddUserDetails.class);
        if (addUserDetails == null) {
            throw new ParameterResolutionException("The test method should be annotated with AddUserDetails");
        }


        if (addUserDetails.value().getTitle().equalsIgnoreCase("db")) {
            final int id = userRepository.getAll().size() + 1;

            userDetails = UserDetailsGenerator.generateUserDetails(id);
            userRepository.createUserDetails(userDetails);
        }

        return userDetails;
    }

    @Override
    public void afterEach(ExtensionContext context) {
        if (ofNullable(userDetails).isPresent()){
            userRepository.deleteUser(userDetails.getId());
        }
    }
}
