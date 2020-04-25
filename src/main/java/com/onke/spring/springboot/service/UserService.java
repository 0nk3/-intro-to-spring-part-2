package com.onke.spring.springboot.service;

public interface UserService {
    String addUser(long id, String name, String surname);
    void remove(long id);
    long getUser(long id);
}
