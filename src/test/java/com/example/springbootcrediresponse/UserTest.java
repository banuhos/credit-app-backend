package com.example.springbootcrediresponse;

import com.example.springbootcrediresponse.entity.User;
import com.example.springbootcrediresponse.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@PropertySource(value = "/src/main/resources/application.properties", ignoreResourceNotFound = true)
public class UserTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public User save() throws Exception {
        User instance = null;
        try {
            instance = mapper.readValue(new File("src/test/resources/json/user.json"), User.class);
            instance = userRepository.save(instance);
        } catch (Exception e) {
            System.out.print(e);
            fail("Hata oluştu");
        }
        return instance;
    }


}
