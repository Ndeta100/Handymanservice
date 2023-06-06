package com.handi.handi_02.controller;

import com.handi.handi_02.entity.HandyMan;
import com.handi.handi_02.service.HandyManService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    @Autowired
    private HandyManService handyManService;

    @GetMapping("/handyman")
    public ResponseEntity<List<HandyMan>> getAllHandyManServices() {

        return ResponseEntity.ok(handyManService.getAllHandyMan());
    }

    @PostMapping("/handyman")
    public ResponseEntity<HandyMan> saveHandyMan(@RequestBody HandyMan handyMan) {
        return ResponseEntity.ok(handyManService.saveHandyMan(handyMan));
    }
}
