package com.example.springbootcrediresponse.Controller;

import com.example.springbootcrediresponse.DTO.UserDTO;
import com.example.springbootcrediresponse.service.CreditService;
import com.example.springbootcrediresponse.service.ScoreService;
import com.example.springbootcrediresponse.service.UserService;
import com.example.springbootcrediresponse.util.GenericResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin(origins="http://localhost:3000")
public class UserController {

    private final UserService userService;
    private final CreditService creditService;
    private final ScoreService scoreService;

    private final int CREDI_LIMIT_MULTIPLIER = 4;

    private String creditResponse;
    private double crediTotal=0;

    @PostMapping("/creditResponse")
    public ResponseEntity<?> save(@RequestBody @Valid UserDTO user) throws Exception {

        GenericResponse message=new GenericResponse("");
        int score = scoreService.getScore(user);
        creditResponse = creditService.getCreditResponce(user,score);

        if(creditResponse.equals("Onay")) {
        if(user.getMounthlyIncome()<5000){
            crediTotal = 10000;
            creditResponse += " Kredi Tutarı: "+crediTotal+" TL";
        }else if(score >=1000){
            crediTotal = user.getMounthlyIncome()*CREDI_LIMIT_MULTIPLIER;
            creditResponse += " Kredi Tutarı: "+ crediTotal+" TL";
        }
        if(userService.findIdentificationNoUser(user)){
            message = new GenericResponse("Daha önceden tanımlı krediniz bulunmaktadır. Kredi Sonucu : Red");
        }else{
            userService.insertUserAndSendMessage(user,crediTotal);
            message =new GenericResponse(" Kredi Sonucu: "+creditResponse);
        }
        }else{
            message = new GenericResponse(" Kredi Sonucu: "+creditResponse);
        }
        return ResponseEntity.ok(message);
    }

}
