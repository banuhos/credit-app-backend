package com.example.springbootcrediresponse;

import com.example.springbootcrediresponse.entity.User;
import com.example.springbootcrediresponse.repository.UserRepository;
import com.example.springbootcrediresponse.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class SpringbootMongodbCrediResultApplication {


    public static void main(String[] args) {
        SpringApplication.run(SpringbootMongodbCrediResultApplication.class, args);
    }

    @Bean
    public CommandLineRunner setup(UserService userService,UserRepository userRepository) {
        return (args) -> {
            userService.deleteAllUsers();
            userRepository.save(new User("1","Admin","Demo","5438008729","12345678912",3500,0));
        };
    }
}
