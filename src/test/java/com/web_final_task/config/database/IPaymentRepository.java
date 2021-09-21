package com.web_final_task.config.database;

import com.web_final_task.entity.Payment;

import java.util.List;

public interface IPaymentRepository {

    void save(Payment payment);

    void deletePayment(long id);

    List<Payment> getAll();

    Payment findById(long id);
}
