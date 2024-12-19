package net.angadSoftwareSolutions.journalAPP.controller;


import jakarta.annotation.PostConstruct;
import net.angadSoftwareSolutions.journalAPP.entity.User;
import net.angadSoftwareSolutions.journalAPP.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    public UserController(){
        System.out.println("User controller initialization");
    }

    @PostConstruct
    public void postConstructMethod(){
        System.out.println("In post contruct method of User controller , hashcode : "+this.hashCode()+" User servcie hashcode : "+userService.hashCode());
    }


    @GetMapping
    @PreAuthorize("ADMIN")
    public ResponseEntity<List<User>> getUsers(){
        List<User> users=userService.getAllUsers();
        if(Objects.nonNull(users)){
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



    @PutMapping("/update-user")
    public ResponseEntity<User> updateUser(@RequestBody User user){
        User updatedUser=userService.updateUser(user);
        if(updatedUser!=null){
            return new ResponseEntity<>(updatedUser,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<User> getUser(@PathVariable ObjectId id){
       User user=userService.getById(id);
       return new ResponseEntity<>(user,HttpStatus.OK);
    }
    @GetMapping("/{username}")
    public ResponseEntity<User> getUser(@PathVariable String username){
        User user=userService.getByUsername(username);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }




}
