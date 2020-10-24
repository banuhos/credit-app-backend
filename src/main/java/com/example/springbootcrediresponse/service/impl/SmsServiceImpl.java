package com.example.springbootcrediresponse.service.impl;

import com.example.springbootcrediresponse.entity.Sms;
import com.example.springbootcrediresponse.service.SmsService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class SmsServiceImpl implements SmsService {
    private String ACCOUNT_SID; // Twillo account SID

    private String AUTH_TOKEN ; // Twillo AUTH_TOKEN

    private String FROM_NUMBER ; // Twillo FROM_NUMBER

    @Override
    public void send(Sms sms) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(new PhoneNumber("+90"+sms.getTo()), new PhoneNumber(FROM_NUMBER), sms.getMessage())
            .create();
        System.out.println("here is my id:"+message.getSid());
    }
}
