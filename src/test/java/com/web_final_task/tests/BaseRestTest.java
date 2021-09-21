package com.web_final_task.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.web_final_task.annotations.extentions.ClearDatabase;
import com.web_final_task.config.database.AddressRepository;
import com.web_final_task.config.database.IAddressRepository;
import com.web_final_task.config.database.IPaymentRepository;
import com.web_final_task.config.database.PaymentRepository;
import com.web_final_task.service.PaymentManagementService;
import com.web_final_task.service.UserManagementService;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith({ClearDatabase.class})
public class BaseRestTest {

    protected final String PATH_TO_PAYMENT_JSONS = "/json/payment/";
    protected final String GENERATED_JSON_PATH = "src/test/resources/__files" + PATH_TO_PAYMENT_JSONS;
    protected PaymentManagementService paymentService = new PaymentManagementService();
    protected UserManagementService managementService = new UserManagementService();
    protected IAddressRepository addressRepository = new AddressRepository();
    protected IPaymentRepository paymentRepository = new PaymentRepository();
    protected ObjectMapper objectMapper = new ObjectMapper();
}

