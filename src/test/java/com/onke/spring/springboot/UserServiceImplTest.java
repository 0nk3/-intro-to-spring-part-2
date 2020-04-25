package com.onke.spring.springboot;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.onke.spring.springboot.model.User;
import com.onke.spring.springboot.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.logging.Logger;


@EnableAutoConfiguration
@SpringBootTest(classes = SpringbootApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

class UserServiceImplTest {
    private static final Logger LOGGER = Logger.getLogger(UserServiceImplTest.class.getName());
    // user details to test with
    private final String  name;
    private final String surname;

    @Autowired
    private UserService userService;

    UserServiceImplTest() {
        name = "Linus";
        surname = "Torvalds";
    }
    @Test
    void addUser() {
        assertEquals("Linus", userService.addUser(1,name, surname));
    }
    @Test
    void remove() {
        //no return type
    }
    @Test
    void getUser() {
        User user =  new User();
        user.setName(name);
        user.setSurname(surname);
        user.setId(1);
        assertEquals(user.getId(), userService.getUser(1));  // same user id ==> same user
    }
    @Test
    void getUserFourTimes(){
        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setId(1);
        for (int i = 1; i <= 4; i++){
            LOGGER.info(String.format("\t\t\t\tcall number  : %d", i));  //keeping track of call counts
            assertEquals(user.getId(), userService.getUser(1));  // same user id ==> same user
        }
    }
}