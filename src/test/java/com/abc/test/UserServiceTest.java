package com.abc.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import com.abc.dao.UserDaoImpl;
import com.abc.model.User;
import com.abc.service.UserService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@JdbcTest
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = { SpringTestConfiguration.class })
public class UserServiceTest {
    @Mock
    private UserDaoImpl userDao;
    private List<User> system1Users = new ArrayList<User>();

    @InjectMocks 
    private UserService userService;
    
    @Autowired
    JdbcTemplate jdbcTemplate1;
    @Autowired
    JdbcTemplate jdbcTemplate2;

    @BeforeEach
    void init() {
    	userDao = new UserDaoImpl(jdbcTemplate1, jdbcTemplate2);
        userService = new UserService(userDao);
        
        /*User user1 = new User(new Long(1), "Test User1");
        userDao.insertSystem1User(user1);
        User user2 = new User(new Long(1), "Test User1");
        userDao.insertSystem1User(user2);
        
        userDao.insert(user1);
        system1Users.add(user1);
        system1Users.add(user2);*/
    }

    @Test
    void testGet() {
        //assertEquals(2, this.userDao.getAllSystem1User().size());
    }

}
