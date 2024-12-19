package net.angadSoftwareSolutions.journalAPP.controller;


import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import net.angadSoftwareSolutions.journalAPP.entity.JournalEntry;
import net.angadSoftwareSolutions.journalAPP.entity.User;
import net.angadSoftwareSolutions.journalAPP.service.JournalEntryService;
import net.angadSoftwareSolutions.journalAPP.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@RestController
//@Scope("prototype") it will create new object when this controller is used everytime
@RequestMapping("/journal")
public class JournalEntryController2 {

    @Autowired
    JournalEntryService journalEntryService;

    @Autowired
    UserService userService;



    public JournalEntryController2(){
        System.out.println("JournalEntryController2 object initialization");
    }



    @InitBinder
    protected void initBinder(DataBinder binder){
       binder.registerCustomEditor(String.class,"userFathersName",new FirstNamePropertyEditor());
    }





    @GetMapping("/getAll")
    public ResponseEntity<List<JournalEntry>> getJournalEntriesByUser(){
        final String username = SecurityContextHolder.getContext().getAuthentication().getName();
        final List<JournalEntry> allJournals = journalEntryService.getAllJournalsByUser(username);
        if(Objects.isNull(allJournals) || allJournals.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(allJournals,HttpStatus.OK);
    }


    @PostMapping()
    public ResponseEntity<JournalEntry> createJournal(@RequestParam(name="userFathersName") String userFathersName ,@RequestBody JournalEntry journalEntry){
        try{
            System.out.println(userFathersName);
            final String username = SecurityContextHolder.getContext().getAuthentication().getName();
            JournalEntry journalEntry1=journalEntryService.save(journalEntry,username);
            return new ResponseEntity<>(journalEntry1,HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/id/{id}")
    public ResponseEntity<JournalEntry> getJournalById(@PathVariable("id") ObjectId id){

       JournalEntry journalEntry=journalEntryService.getById(id);
       if(Objects.isNull(journalEntry)){
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
       else{
           return new ResponseEntity<>(journalEntry,HttpStatus.OK);
       }
    }


    @PutMapping("/{id}")
    public ResponseEntity<JournalEntry> updateJournal(@PathVariable ObjectId id,@RequestBody JournalEntry journalEntry){
        JournalEntry journalEntry1=journalEntryService.updateJournalEntry(id,journalEntry);
        if(Objects.isNull(journalEntry1)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(journalEntry1,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteJournal(@PathVariable ObjectId id){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        boolean removed= journalEntryService.removeJournalEntry(id, username);

        if (removed){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

class FirstNamePropertyEditor extends PropertyEditorSupport{
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(text.toLowerCase());
    }
}

