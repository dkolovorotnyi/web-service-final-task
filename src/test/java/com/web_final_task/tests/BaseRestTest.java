package com.web_final_task.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.web_final_task.annotations.extentions.ClearDatabase;
import com.web_final_task.annotations.extentions.WireMockServerExtension;
import com.web_final_task.config.database.AddressRepository;
import com.web_final_task.config.database.IAddressRepository;
import com.web_final_task.config.database.IPaymentRepository;
import com.web_final_task.config.database.PaymentRepository;
import com.web_final_task.service.PaymentManagementService;
import com.web_final_task.service.UserManagementService;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.File;

@ExtendWith({ClearDatabase.class, WireMockServerExtension.class})
public class BaseRestTest {

    protected static final String PATH_TO_PAYMENT_JSONS = "/json/payment/";
    protected static final String GENERATED_JSON_PATH = "src/test/resources/__files" + PATH_TO_PAYMENT_JSONS;
    protected PaymentManagementService paymentService = new PaymentManagementService();
    protected UserManagementService managementService = new UserManagementService();
    protected IAddressRepository addressRepository = new AddressRepository();
    protected IPaymentRepository paymentRepository = new PaymentRepository();
    protected ObjectMapper objectMapper = new ObjectMapper();


    @AfterAll
    @SneakyThrows
    static void deleteMockJsons() {
        FileUtils.cleanDirectory(new File(GENERATED_JSON_PATH));
    }
}

