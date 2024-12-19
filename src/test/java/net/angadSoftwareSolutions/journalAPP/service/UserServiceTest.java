package net.angadSoftwareSolutions.journalAPP.service;

import net.angadSoftwareSolutions.journalAPP.entity.User;
import net.angadSoftwareSolutions.journalAPP.repository.UserRepo;
import org.apache.catalina.realm.UserDatabaseRealm;
import org.bson.assertions.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {
//
//    @Autowired
//    UserRepo userRepo;
//
//    @Autowired
//    UserService userService;
//
//    @ParameterizedTest
//    @ArgumentsSource(UserArgumentProvider.class)
//    public void testSaveNewEntryUser(User user){
//        assertTrue(userService.save2(user));
//    }
//
//    @ParameterizedTest
//    @ValueSource(strings={
//            "Abhay","Angadbir0987","Rasleenuuu"
//    })
//    public void test(String username){
//         User angadbir0987 = userRepo.findByUsername(username);
//         assertNotNull(angadbir0987,"failed for : "+username);
//     }
//
//
//     @Disabled
//     @ParameterizedTest
//     @CsvSource({
//             "1,1,2",
//             "2,2,4",
//             "3,3,9"
//     })
//     public void test2(int a,int b,int expected){
//         assertEquals(expected,a+b);
//     }
}
