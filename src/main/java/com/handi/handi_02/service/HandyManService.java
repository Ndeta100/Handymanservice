package com.handi.handi_02.service;

import com.handi.handi_02.entity.HandyMan;
import com.handi.handi_02.repository.HandyManRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HandyManService {
    @Autowired
    private HandyManRepository handyManRepository;


    public List<HandyMan> getAllHandyMan(){
        return this.handyManRepository.findAll();
    }

    public HandyMan saveHandyMan(HandyMan handyMan) {
        return handyManRepository.save(handyMan);
    }
}
