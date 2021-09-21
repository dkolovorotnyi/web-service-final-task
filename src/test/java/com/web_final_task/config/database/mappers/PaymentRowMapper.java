package com.web_final_task.config.database.mappers;

import com.web_final_task.entity.Payment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentRowMapper implements RowMapper<Payment> {

    @Override
    public Payment mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Payment.builder()
                .id(rs.getLong("id"))
                .userId(rs.getLong("user_id"))
                .cardNumber(rs.getString("card_number"))
                .cardHolder(rs.getString("cardholder"))
                .cvv(rs.getString("cvv"))
                .expiryMonth(rs.getInt("expiry_month"))
                .expiryYear(rs.getInt("expiry_year"))
                .verified(true)
                .build();
    }
}
