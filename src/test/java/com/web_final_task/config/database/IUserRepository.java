package com.web_final_task.config.database;

import com.web_final_task.entity.xml.UserDetails;

import java.util.List;

public interface IUserRepository {

    void createUserDetails(UserDetails userDetails);
    void deleteUser(long id);
    List<UserDetails> getAll();
}
