package com.example.springbootcrediresponse.service.impl;

import com.example.springbootcrediresponse.DTO.UserDTO;
import com.example.springbootcrediresponse.entity.Sms;
import com.example.springbootcrediresponse.entity.User;
import com.example.springbootcrediresponse.repository.UserRepository;
import com.example.springbootcrediresponse.service.SmsService;
import com.example.springbootcrediresponse.service.UserService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    private SmsService smsService;
    private SimpMessagingTemplate webSocket;
    private MongoTemplate mongoTemplate;
    private final String  TOPIC_DESTINATION = "/topic/sms";

    @Lazy
    public UserServiceImpl(UserRepository userRepository,SmsService smsService,SimpMessagingTemplate webSocket,MongoTemplate mongoTemplate) {
        this.userRepository = userRepository;
        this.smsService=smsService;
        this.webSocket=webSocket;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void insertUserAndSendMessage(UserDTO userDTO,double crediTotal) {

        User user = User.getBuilder()
            .name(userDTO.getName())
            .surname(userDTO.getSurname())
            .phoneNumber(userDTO.getPhoneNumber())
            .mounthlyIncome(userDTO.getMounthlyIncome())
            .identificationNumber(userDTO.getIdentificationNumber())
            .credit(crediTotal)
            .build();

        Sms sms = new Sms();
        sms.setTo(user.getPhoneNumber());
        sms.setMessage("Tebrikler krediniz onaylanmıştır. Kredi tutarınız " + crediTotal + " TL dir.");
        /*
        try {
            smsService.send(sms);
        } catch(Exception e){
            webSocket.convertAndSend(TOPIC_DESTINATION, getTimeStamp() + ": Error sending the SMS: "+e.getMessage());
            throw e;
        }*/
        userRepository.save(user);

    }

    @Override
    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    @Override
    public boolean findIdentificationNoUser(UserDTO userDTO) {
        boolean isHaveUser=false;
        Query query = new Query();
        User user = User.getBuilder()
            .name(userDTO.getName())
            .surname(userDTO.getSurname())
            .phoneNumber(userDTO.getPhoneNumber())
            .mounthlyIncome(userDTO.getMounthlyIncome())
            .identificationNumber(userDTO.getIdentificationNumber())
            .credit(userDTO.getCredit())
            .build();

        query.addCriteria(Criteria.where("identificationNumber").is(user.getIdentificationNumber()));
        List<User> users = mongoTemplate.find(query, User.class);
        if(users.size()>0){
            isHaveUser = true;
        }
        return isHaveUser;
    }

    private String getTimeStamp() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
    }





}
