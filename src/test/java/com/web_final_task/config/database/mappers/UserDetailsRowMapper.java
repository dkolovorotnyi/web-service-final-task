package com.web_final_task.config.database.mappers;

import com.web_final_task.entity.xml.UserDetails;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDetailsRowMapper implements RowMapper<UserDetails> {
    @Override
    public UserDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
        return UserDetails.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .lastName(rs.getString("last_name"))
                .email(rs.getString("email"))
                .birthday(null)
                .build();
    }
}
