package com.onke.spring.springboot.dao;

import com.onke.spring.springboot.model.User;

public interface FakeRepoInterface {
    void insertUser (long id, String name, String surname);
    User findById(long id);
    void deleteUser(long id);
}
