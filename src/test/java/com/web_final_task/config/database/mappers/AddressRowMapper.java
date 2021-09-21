package com.web_final_task.config.database.mappers;

import com.web_final_task.entity.Address;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressRowMapper implements RowMapper<Address> {

    @Override
    public Address mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Address.builder()
                .id(rs.getLong("id"))
                .userId(rs.getLong("user_id"))
                .addressLine1(rs.getString("line_one"))
                .addressLine2(rs.getString("line_two"))
                .city(rs.getString("city"))
                .zip(rs.getString("zip"))
                .state(rs.getString("state"))
                .build();
    }
}
