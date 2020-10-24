package com.example.springbootcrediresponse.service.impl;

import com.example.springbootcrediresponse.DTO.UserDTO;
import com.example.springbootcrediresponse.service.ScoreService;
import java.util.Random;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class ScoreServiceImpl implements ScoreService {

    private Random random = new Random();

    @Override
    public int getScore(UserDTO user) {
        return random.nextInt(1100 + 1 - 1) + 1;
    }
}
