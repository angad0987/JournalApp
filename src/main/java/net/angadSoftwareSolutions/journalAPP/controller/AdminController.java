package net.angadSoftwareSolutions.journalAPP.controller;


import net.angadSoftwareSolutions.journalAPP.entity.User;
import net.angadSoftwareSolutions.journalAPP.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/admin")
public class AdminController {


    @Autowired
    UserService userService;

    @GetMapping("/all-users")
    @PreAuthorize("ADMIN")
    public ResponseEntity<List<User>> getUsers(){
        List<User> users=userService.getAllUsers();
        if(Objects.nonNull(users)){
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
