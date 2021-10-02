package com.web_final_task.config.database;

import com.web_final_task.config.database.mappers.AddressRowMapper;
import com.web_final_task.entity.Address;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class AddressRepository implements IAddressRepository {

    private final String INSERT_ADDRESS = "INSERT INTO address (id, user_id, line_one, line_two, city, zip, state) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private final String GET_ALL_ADDRESSES = "SELECT * FROM address";
    private final JdbcTemplate jdbcTemplate = new JdbcTemplate(DataSourceProvider.INSTANCE.getDataSource());

    public void save(Address address) {
        jdbcTemplate.update(INSERT_ADDRESS, address.getId(), address.getUserId(), address.getAddressLine1(),
                address.getAddressLine2(), address.getCity(), address.getZip(), address.getState());
    }

    public List<Address> getAll() {
        return jdbcTemplate.query(GET_ALL_ADDRESSES, new AddressRowMapper());
    }
}
