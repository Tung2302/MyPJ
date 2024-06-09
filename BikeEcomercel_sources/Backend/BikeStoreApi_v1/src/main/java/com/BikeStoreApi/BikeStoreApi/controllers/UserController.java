package com.BikeStoreApi.BikeStoreApi.controllers;

import com.BikeStoreApi.BikeStoreApi.dtos.UserDTO;
import com.BikeStoreApi.BikeStoreApi.entities.User;
import com.BikeStoreApi.BikeStoreApi.entities.ResponseObject;
import com.BikeStoreApi.BikeStoreApi.repositories.UserRepository;
import com.BikeStoreApi.BikeStoreApi.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/Users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ModelMapper modelMapper;



//    @PostMapping("/login")
//    public ResponseEntity<ResponseObject> logginUser(@RequestBody LoginDTO loginDTO) {
//        Optional<User> user = userService.findUserByEmail(loginDTO.getEmail());
//        if (user != null) {
//            if (passwordEncoder.matches(loginDTO.getPassword(), user.get().getPassword())) {
//                return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Successed", "Login successed", user));
//            }
//            else {
//                return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Failed", "Password is not correct", null));
//            }
//        } else {
//            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Failed", "Cant find your email", null));
//        }
//    }
//
    //Lay ra tat ca nick nv
    @GetMapping("")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
    @GetMapping("/latest")
    public List<User> getAllLatestUsers() {
        return userService.getAllLatestUsers();
    }
    //Tao nick moi
    @PostMapping("/save")
    public ResponseEntity<ResponseObject> addEUser(@RequestBody UserDTO userDTO) {
        Optional<User> foundUser = userRepository.findByEmail(userDTO.getEmail().trim());
        if (foundUser!=null) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("Failed", "Can't add new user because an user with the same email already exists", null)
            );
        } else if (userDTO.getRoleId() ==null ) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("Failed", "Can't add new user because the role cannot be empty", null)
            );
        } else {
            User savedUser = userService.saveUser(userDTO);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("Successed", "User inserted successfully", savedUser)
            );
        }
    }

    //Tim tai khoan by id
    @GetMapping("/{id}")
    //let return an pbj witf:data, message, status
    ResponseEntity<ResponseObject> findById(@PathVariable Integer id) {
        Optional<User> foundUser = userRepository.findById(id);
        return foundUser.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("OK", "Querry User successfully", foundUser)
                ) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("FAILED", "Cant not find User with id = " + id, "")
                );
    };
    //sua tai khoan nv
    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateUser(@RequestBody User selectedUser, @PathVariable Integer id){
        boolean existedUser = userService.checkExistId(id);
        if(existedUser==false){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("Failed", "User not found", selectedUser)
            );
        }
        else{
            userService.updateUser(selectedUser);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("OK", "User update successfully", selectedUser)
            );
        }
    }
    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> deleteUser(@PathVariable Integer id){
        boolean exists = userService.checkExistId(id);
        if(exists){
            userService.deleteUser(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("Successed","Delete User Successfully","")
            );
        }
        return  ResponseEntity.status((HttpStatus.NOT_FOUND)).body(
                new ResponseObject("Failed","Cant find User to delete","")

        );

    };

    @GetMapping("/get-user-by-email")
    public ResponseEntity<ResponseObject> getUserByEmail(@RequestBody String email){
        Optional<User> user = userService.findUserByEmail(email);
        if(user.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("Successed","Retrieve User Successfully",user));
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("Failed","Cant find User by this email",null));
        }
    }

    @GetMapping("/get-user-by-token")
    public ResponseEntity<ResponseObject> getUserByToken(@RequestHeader(name = "Authorization") String authorizationHeader) {
        try {
            Optional<User> user = userService.getByToken(authorizationHeader);
            if (user.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("Success", "Retrieve User Successfully", user));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("Failed", "Cannot find User by this token", null));
            }
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ResponseObject("Failed", e.getMessage(), null));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
