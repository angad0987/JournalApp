package net.angadSoftwareSolutions.journalAPP.service;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import net.angadSoftwareSolutions.journalAPP.controller.PublicController;
import net.angadSoftwareSolutions.journalAPP.entity.JournalEntry;
import net.angadSoftwareSolutions.journalAPP.entity.User;
import net.angadSoftwareSolutions.journalAPP.repository.JournalEntryRepo;
import net.angadSoftwareSolutions.journalAPP.repository.UserRepo;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
//default scope is singleton means object is created per IOC means per application
//@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
//@Scope(value="request",proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserService {
    @Autowired
    UserRepo userRepo;


//    private static final Logger logger= LoggerFactory.getLogger(UserService.class);
//

    @Autowired
    PasswordEncoder passwordEncoder;

    public UserService(){
        System.out.println("User service initialization");
    }

    @PostConstruct
    public void postConstruct(){
        System.out.println("In post contruct of User service , hashcode : "+this.hashCode()+" , "+" User repo hashcode : "+userRepo.hashCode()+" User hashcode : ");

    }

    public void hii(){

    }

    public User save(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(List.of("USER"));
        return userRepo.save(user);
    }
    public boolean save2(User user){
        try{
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(List.of("USER"));
            userRepo.save(user);
            return true;
        }catch (Exception e){
            log.error("Error occured for {} ",user.getUsername());
            log.debug("Error occured for {} ",user.getUsername());
            log.trace("Error occured for {} ",user.getUsername());
            log.debug("Error occured for {} ",user.getUsername());
            return false;
        }

    }
    public List<User> getAllUsers(){
        return userRepo.findAll();
    }
    public User updateUser( User user){
        final String username =SecurityContextHolder.getContext().getAuthentication().getName();
        User user1=userRepo.findByUsername(username);
        if(user1!=null){
           user1.setUsername(user.getUsername()!=null ? user.getUsername() : user1.getUsername());
           user1.setPassword(passwordEncoder.encode(user.getPassword()!=null ? user.getPassword() : user1.getPassword()));
            return userRepo.save(user1);
        }
        return null;

    }
    public void ha(){

    }
    public void deleteUser(ObjectId id){
        Optional<User> user=userRepo.findById(id);
        user.ifPresent(entry -> userRepo.delete(entry));
    }
    public User getById(ObjectId id){
        return userRepo.findById(id).get();
    }
    public User getByUsername(String username){
        return userRepo.findByUsername(username);
    }
}
