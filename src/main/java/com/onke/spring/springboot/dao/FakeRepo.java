package com.onke.spring.springboot.dao;

import com.onke.spring.springboot.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static java.lang.String.*;


@Repository
public class FakeRepo implements FakeRepoInterface {
    private static final Logger LOGGER = LogManager.getLogger(FakeRepo.class.getName());

    ArrayList<User> users = new ArrayList<>();

    // add user
    @Override
    public void insertUser(long id, String name, String surname) {
        users.add(new User(id, name, surname));
        LOGGER.info(format("name : %s", name));
    }
    // find a user given an id
    @Override
    public User findById(long id) {
        for (User user : users) {
            if(user.getId() == id){
                LOGGER.info("User Found : " + user.getName());
                return user;
            }
        }
        LOGGER.error("User Not Found");
        return null;
    }
    // remove a user given an id
    @Override
    public void deleteUser(long id) {
        users.removeIf(user -> user.getId() == id);
    }
    // print out user information
    @Override
    public String toString() {
        return "FakeRepo{" +
                "users=" + users +
                '}';
    }
}
