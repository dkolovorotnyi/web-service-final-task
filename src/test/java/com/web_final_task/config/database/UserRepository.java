package com.web_final_task.config.database;

import com.web_final_task.config.database.mappers.UserDetailsRowMapper;
import com.web_final_task.entity.xml.UserDetails;
import com.web_final_task.utility.DateConvertor;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.util.List;


public class UserRepository implements IUserRepository {

    private final String INSERT_USER_DETAILS = "INSERT INTO user (id, birthday, name, last_name, email) VALUES (?, ?, ?, ?, ?)";
    private final String GET_ALL_USER_DETAILS = "SELECT * FROM user";
    private final String REMOVE_USER_DETAILS_BY_ID = "DELETE FROM user WHERE id = ?";

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(DataSourceProvider.INSTANCE.getDataSource());

    @Override
    public void createUserDetails(UserDetails userDetails) {
        final LocalDate birthday = DateConvertor.convertBirthdayToLocalDateTime(userDetails);
        jdbcTemplate.update(INSERT_USER_DETAILS, userDetails.getId(), birthday.toString(), userDetails.getName(),
                userDetails.getLastName(), userDetails.getEmail());
    }

    @Override
    public void deleteUser(long id) {
        jdbcTemplate.update(REMOVE_USER_DETAILS_BY_ID, id);
    }

    @Override
    public List<UserDetails> getAll() {
        return jdbcTemplate.query(GET_ALL_USER_DETAILS, new UserDetailsRowMapper());
    }
}
