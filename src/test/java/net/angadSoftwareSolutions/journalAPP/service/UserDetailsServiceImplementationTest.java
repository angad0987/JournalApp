package net.angadSoftwareSolutions.journalAPP.service;


import net.angadSoftwareSolutions.journalAPP.entity.User;
import net.angadSoftwareSolutions.journalAPP.repository.UserRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

import static org.mockito.Mockito.*;


public class UserDetailsServiceImplementationTest {

    @InjectMocks
    CustomUserDetailsServiceImpl customUserDetailsService;

    @Mock
    UserRepo userRepo;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Disabled
    @Test
    public void loadUserByUsernameByTest(){
        when(userRepo.findByUsername("Angadbir0987")).thenReturn(User.builder().username("Angadbir0987").password("2343JKBLBUbcluib$$$$%@TVYBVB&G").roles(new ArrayList<>()).build());

        UserDetails userDetails = customUserDetailsService.loadUserByUsername("Angadbir0987");
        Assertions.assertNotNull(userDetails,"Error in loading user with username : "+"Angadbir0987");



    }
}
