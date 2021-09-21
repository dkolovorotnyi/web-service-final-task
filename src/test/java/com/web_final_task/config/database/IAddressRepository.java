package com.web_final_task.config.database;

import com.web_final_task.entity.Address;

import java.util.List;

public interface IAddressRepository {

    void save(Address address);
    void deleteAddress(long id);
    List<Address> getAll();

    Address findById(long id);
}
