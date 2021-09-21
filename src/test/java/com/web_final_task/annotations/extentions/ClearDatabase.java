package com.web_final_task.annotations.extentions;

import com.web_final_task.config.database.IPaymentRepository;
import com.web_final_task.config.database.PaymentRepository;
import com.web_final_task.entity.Payment;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.util.List;

public class ClearDatabase implements AfterAllCallback {

    private IPaymentRepository paymentRepository = new PaymentRepository();

    @Override
    public void afterAll(ExtensionContext context) {
        final List<Payment> paymentList = paymentRepository.getAll();

        if (!paymentList.isEmpty()) {
            paymentList.forEach(payment -> paymentRepository.deletePayment(payment.getId()));
        }
    }
}
