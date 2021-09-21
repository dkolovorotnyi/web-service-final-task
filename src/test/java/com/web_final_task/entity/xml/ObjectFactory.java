
package com.web_final_task.entity.xml;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.web_final_task.entity.xml package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.web_final_task.entity.xml
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CreateUserRequest }
     * 
     */
    public CreateUserRequest createCreateUserRequest() {
        return new CreateUserRequest();
    }

    /**
     * Create an instance of {@link UserDetails }
     * 
     */
    public UserDetails createUserDetails() {
        return new UserDetails();
    }

    /**
     * Create an instance of {@link CreateUserRequest.Addresses }
     * 
     */
    public CreateUserRequest.Addresses createCreateUserRequestAddresses() {
        return new CreateUserRequest.Addresses();
    }

    /**
     * Create an instance of {@link CreateUserRequest.Payments }
     * 
     */
    public CreateUserRequest.Payments createCreateUserRequestPayments() {
        return new CreateUserRequest.Payments();
    }

    /**
     * Create an instance of {@link CreateUserResponse }
     * 
     */
    public CreateUserResponse createCreateUserResponse() {
        return new CreateUserResponse();
    }

    /**
     * Create an instance of {@link GetUserDetailsRequest }
     * 
     */
    public GetUserDetailsRequest createGetUserDetailsRequest() {
        return new GetUserDetailsRequest();
    }

    /**
     * Create an instance of {@link GetUserDetailsResponse }
     * 
     */
    public GetUserDetailsResponse createGetUserDetailsResponse() {
        return new GetUserDetailsResponse();
    }

    /**
     * Create an instance of {@link UpdateUserRequest }
     * 
     */
    public UpdateUserRequest createUpdateUserRequest() {
        return new UpdateUserRequest();
    }

    /**
     * Create an instance of {@link UpdateUserResponse }
     * 
     */
    public UpdateUserResponse createUpdateUserResponse() {
        return new UpdateUserResponse();
    }

    /**
     * Create an instance of {@link DeleteUserRequest }
     * 
     */
    public DeleteUserRequest createDeleteUserRequest() {
        return new DeleteUserRequest();
    }

    /**
     * Create an instance of {@link DeleteUserResponse }
     * 
     */
    public DeleteUserResponse createDeleteUserResponse() {
        return new DeleteUserResponse();
    }

    /**
     * Create an instance of {@link NewAddress }
     * 
     */
    public NewAddress createNewAddress() {
        return new NewAddress();
    }

    /**
     * Create an instance of {@link ExistingAddress }
     * 
     */
    public ExistingAddress createExistingAddress() {
        return new ExistingAddress();
    }

    /**
     * Create an instance of {@link NewPayment }
     * 
     */
    public NewPayment createNewPayment() {
        return new NewPayment();
    }

    /**
     * Create an instance of {@link ExistingPayment }
     * 
     */
    public ExistingPayment createExistingPayment() {
        return new ExistingPayment();
    }

    /**
     * Create an instance of {@link UserDetails.Addresses }
     * 
     */
    public UserDetails.Addresses createUserDetailsAddresses() {
        return new UserDetails.Addresses();
    }

    /**
     * Create an instance of {@link UserDetails.Payments }
     * 
     */
    public UserDetails.Payments createUserDetailsPayments() {
        return new UserDetails.Payments();
    }

}
