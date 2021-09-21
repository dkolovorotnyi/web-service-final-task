package com.web_final_task.config.database;

import com.web_final_task.config.database.mappers.PaymentRowMapper;
import com.web_final_task.entity.Payment;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class PaymentRepository implements IPaymentRepository {

    private final String INSERT_PAYMENT = "INSERT INTO payment (id, user_id, card_number, cardholder, cvv, expiry_month, expiry_year, token) VALUES (?, ?, ?, ?, ?, ?, ?, 'someTokenHere')";
    private final String REMOVE_PAYMENT_BY_ID = "DELETE FROM payment WHERE id = ?";
    private final String FIND_PAYMENT_BY_ID = "SELECT FROM payment WHERE id = ?";
    private final String GET_ALL_PAYMENT = "SELECT * FROM payment";

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(DataSourceProvider.INSTANCE.getDataSource());

    @Override
    public void save(Payment payment) {
        jdbcTemplate.update(INSERT_PAYMENT, payment.getId(), payment.getUserId(), payment.getCardNumber(),
                payment.getCardHolder(), payment.getCvv(), payment.getExpiryMonth(), payment.getExpiryYear());
    }

    @Override
    public void deletePayment(long id) {
        jdbcTemplate.update(REMOVE_PAYMENT_BY_ID, id);
    }

    @Override
    public List<Payment> getAll() {
        return jdbcTemplate.query(GET_ALL_PAYMENT, new PaymentRowMapper());
    }

    @Override
    public Payment findById(long id) {
        return jdbcTemplate.queryForObject(FIND_PAYMENT_BY_ID, new PaymentRowMapper(), id);
    }
}
