package com.example.springbootcrediresponse.service;

import com.example.springbootcrediresponse.DTO.UserDTO;

public interface UserService {

    //User getUserId(long id);

    void insertUserAndSendMessage(UserDTO userDTO,double crediTotal);

    void deleteAllUsers();

    boolean findIdentificationNoUser(UserDTO user);

}
