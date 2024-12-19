package net.angadSoftwareSolutions.journalAPP.controller;

import net.angadSoftwareSolutions.journalAPP.entity.User;
import net.angadSoftwareSolutions.journalAPP.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    UserService userService;




    @PostMapping("create-user")
    public ResponseEntity<String> saveUser(@RequestBody User user){
        boolean b=userService.save2(user);
        if(b){
            return new ResponseEntity<>("User saved successfully", HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/check-health")
    public String getHealth(){
        return "Health is good";
    }
}
