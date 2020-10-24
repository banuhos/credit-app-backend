package com.example.springbootcrediresponse.service;

import com.example.springbootcrediresponse.DTO.UserDTO;

public interface CreditService {

    String getCreditResponce(UserDTO user,int score);
}
