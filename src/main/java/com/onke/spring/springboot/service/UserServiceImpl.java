package com.onke.spring.springboot.service;

import com.onke.spring.springboot.dao.FakeRepoInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class.getName());
    // constructor dependency injection
    FakeRepoInterface fakeRepo;
    @Autowired
    public UserServiceImpl(FakeRepoInterface fakeRepo) {
        this.fakeRepo = fakeRepo;
    }

    @Override
    public String addUser(long id, String name, String surname) {
        fakeRepo.insertUser(1, name, surname);
        LOGGER.info(format("%s Entered", name));
        return name;
    }
    @Override
    public void remove(long id) {
        fakeRepo.deleteUser(id);
        LOGGER.info(format("%s removed", getUser(id) ));
    }


    @Override
    @Cacheable("user")
    public long getUser(long id) {
        LOGGER.info("Hello " + fakeRepo.findById(id).getName());
        fakeRepo.findById(id);
        try{
            LOGGER.info("Going to sleep for 5 seconds . . . to simulate backend call.");
            Thread.sleep(1000*5);  // sleep for 3 seconds
        }catch (InterruptedException interruptedException){
            LOGGER.error(interruptedException.getMessage());
        }
        return id;
    }
}
