package com.BikeStoreApi.BikeStoreApi.controllers;

import com.BikeStoreApi.BikeStoreApi.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("demo")
@RequiredArgsConstructor
public class DemoController {
    private final AuthenticationService authenticationService;

    @GetMapping("test")
    public ResponseEntity<String> go(){
        return ResponseEntity.ok("Phan quyen admin moi vo dc");


    }
}
