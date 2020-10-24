package com.example.springbootcrediresponse.service.impl;

import com.example.springbootcrediresponse.DTO.UserDTO;
import com.example.springbootcrediresponse.service.CreditService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class CreditServiceImpl implements CreditService {
    @Override
    public String getCreditResponce(UserDTO user, int score) {
        String responce="";
        if(score<500){
            responce="Red";
        }else if((score>500 && score<1000 && user.getMounthlyIncome()<5000) || score>=1000) {
            responce="Onay";
        }
        return responce;
    }
}

