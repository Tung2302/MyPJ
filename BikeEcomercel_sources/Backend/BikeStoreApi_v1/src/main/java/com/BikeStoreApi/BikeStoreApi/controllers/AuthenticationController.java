package com.BikeStoreApi.BikeStoreApi.controllers;

import com.BikeStoreApi.BikeStoreApi.auths.AuthenticaionResponse;
import com.BikeStoreApi.BikeStoreApi.auths.AuthenticationRequest;
import com.BikeStoreApi.BikeStoreApi.dtos.RegisterDTO;
import com.BikeStoreApi.BikeStoreApi.entities.ResponseObject;
import com.BikeStoreApi.BikeStoreApi.entities.Role;
import com.BikeStoreApi.BikeStoreApi.entities.User;
import com.BikeStoreApi.BikeStoreApi.services.AuthenticationService;
import com.BikeStoreApi.BikeStoreApi.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @PostMapping("/login")
    public ResponseEntity<ResponseObject> login(@RequestBody AuthenticationRequest authenticationRequest){
        AuthenticaionResponse authenticaionResponse = authenticationService.authenticate(authenticationRequest);
        ResponseObject responseObject = new ResponseObject("Successed", "Login successfully", authenticaionResponse);
        return ResponseEntity.ok(responseObject);
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseObject> registerUser(@RequestBody RegisterDTO registerDTO) {
        Optional<User> foundUser = userService.findUserByEmail(registerDTO.getRegisterEmail());
        if (foundUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("Failed", "email already exists", null));
        }else {
            User user = new User();
            user.setEmail(registerDTO.getRegisterEmail());
            user.setPassword(passwordEncoder.encode(registerDTO.getRegisterPassword()));
            user.setPhone(registerDTO.getRegisterPhone());
            user.setFirst_name(registerDTO.getRegisterFirstname());
            user.setLast_name(registerDTO.getRegisterLastname());
            user.setDisable(false);
            user.addRole(new Role(3));
            userService.registerUser(user);

            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("Successed", "User register successfully", user));

        }
    }
}
