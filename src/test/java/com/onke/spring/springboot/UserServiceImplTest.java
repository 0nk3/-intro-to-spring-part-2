package com.onke.spring.springboot;

import com.onke.spring.springboot.model.User;
import com.onke.spring.springboot.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(classes = SpringbootApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableCaching
class UserServiceImplTest {
    private static final Logger LOGGER = Logger.getLogger(UserServiceImplTest.class.getName());
    @LocalServerPort
    private int localServerPort;

    // test security
    @Autowired
    private TestRestTemplate restTemplate;
    // user details to test with
    private final String  name;
    private final String surname;

    @Autowired
    private UserService userService;

    UserServiceImplTest() throws MalformedURLException {
        name = "Linus";
        surname = "Torvalds";
        this.address = new URL("http://localhost:" + localServerPort);

    }
    @Test
    void addUser() {
        assertEquals("Linus", userService.addUser(1,name, surname));
    }
    @Test
    void remove() {
        //no return type
    }
    @Cacheable("name")
    @Test
    public void getUser() {
        User user =  new User();
        user.setName(name);
        user.setSurname(surname);
        user.setId(1);
        assertEquals(user.getId(), userService.getUser(1));  // same user id ==> same user
    }
    // test for the caching section
    @Test
    void getUserFourTimes(){
        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setId(1);
        for (int i = 1; i <= 4; i++){
            assertEquals(user.getId(), userService.getUser(1));  // same user id ==> same user
        }
    }
    // test for the security section
    private URL address;
    @BeforeEach
    public void createLink() throws Exception {
        this.address = new URL("http://127.0.0.1:" + localServerPort);
    }
    @Test
    void testSecurity(){
        ResponseEntity<String> responseEntity = restTemplate.withBasicAuth("onke", "password").getForEntity(address.toString(), String.class);
        assertEquals(HttpStatus.Series.CLIENT_ERROR, responseEntity.getStatusCode().series());

    }

}