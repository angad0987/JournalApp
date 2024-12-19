package net.angadSoftwareSolutions.journalAPP.service;

import net.angadSoftwareSolutions.journalAPP.entity.JournalEntry;
import net.angadSoftwareSolutions.journalAPP.entity.User;
import net.angadSoftwareSolutions.journalAPP.repository.JournalEntryRepo;
import net.angadSoftwareSolutions.journalAPP.repository.UserRepo;
import net.bytebuddy.asm.Advice;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;


public class Journal_Service_Test {


    @InjectMocks
    JournalEntryService journalEntryService;


    @Mock
    UserRepo userRepo;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @ParameterizedTest
    @MethodSource("provideTestScenerios")
    public void getAllJournalsByUser(String username,int expectedEntryCount,boolean userExists){

        //setup mock user
        User userMock=null;
        if(userExists){
            userMock=new User();
            userMock.setUsername(username);
            userMock.setPassword("in3r4r8y1y4bKJHB87&^%%$^");
            userMock.setRoles(List.of("USER","ADMIN"));
            userMock.setId(new ObjectId("aaaaaaaaaaaaaaaaaaaaaaaa"));
            List<JournalEntry> journalEntries=new ArrayList<>();

            for(int i=0;i<expectedEntryCount;i++){
                journalEntries.add(new JournalEntry());
            }
            userMock.setEntryList(journalEntries);
        }
        //configure mock behaviour
        when(userRepo.findByUsername(username)).thenReturn(userMock);

        //invoke service method
        List<JournalEntry> journalEntries1=journalEntryService.getAllJournalsByUser(username);

        //Assertions
        if(userExists){
            //when user exists
            assertNotNull(journalEntries1,"Null list is associated with this user : "+username);
            assertEquals(expectedEntryCount,journalEntries1.size(),"Incorrect number of journal entries for user : "+username);

        }
        else{
            //when user doesnot exists
            assertNull(journalEntries1,"Expected null for non existent user : "+username);

        }

        //verify interaction with user repo
        verify(userRepo,times(1)).findByUsername(username);



    }

    private static Stream<Arguments> provideTestScenerios(){
        return Stream.of(Arguments.of("Abhay",2,true),
                Arguments.of("Angadbir0987",4,true),
                Arguments.of("NonExistedUser",0,false));
    }

}
