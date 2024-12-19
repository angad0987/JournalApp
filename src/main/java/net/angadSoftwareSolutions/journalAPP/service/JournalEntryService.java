package net.angadSoftwareSolutions.journalAPP.service;

import net.angadSoftwareSolutions.journalAPP.entity.JournalEntry;
import net.angadSoftwareSolutions.journalAPP.entity.User;
import net.angadSoftwareSolutions.journalAPP.repository.JournalEntryRepo;
import net.angadSoftwareSolutions.journalAPP.repository.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
//@Scope(value="request",proxyMode = ScopedProxyMode.TARGET_CLASS)
//@Scope("session")
public class JournalEntryService {

    @Autowired
     JournalEntryRepo journalEntryRepo;

    @Autowired
    UserRepo userRepo;

    public JournalEntryService(){
        System.out.println("Journal entry service initialization");
    }


    public JournalEntry save(JournalEntry journalEntry, String username){



        try{

            User user=userRepo.findByUsername(username);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry savedEntry=journalEntryRepo.save(journalEntry);
            user.getEntryList().add(savedEntry);
//            user.setUsername(null);
            userRepo.save(user);
            return savedEntry;
        }catch (Exception ex){
            System.out.println(ex);
            throw new RuntimeException("Error while saving the journal");
        }

    }
    public List<JournalEntry> getAllJournalsByUser(String username){
        User user=userRepo.findByUsername(username);
        if(user!=null){
            return user.getEntryList();
        }
        else{
            return null;
        }

    }
    public JournalEntry updateJournalEntry(ObjectId id,JournalEntry journalEntry){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        User user=userRepo.findByUsername(username);
        List<JournalEntry> journalEntries=user.getEntryList().stream().filter(entry -> entry.getId().equals(id)).collect(Collectors.toList());
        if(!journalEntries.isEmpty()){
            JournalEntry journalEntry1=journalEntryRepo.findById(id).orElse(null);
                journalEntry1.setTitle(journalEntry.getTitle()!=null?journalEntry.getTitle():journalEntry1.getTitle());
                journalEntry1.setContent(journalEntry.getContent()!=null?journalEntry.getContent():journalEntry1.getContent());

                return journalEntryRepo.save(journalEntry1);
        }
        return null;

    }

    @Transactional
    public boolean removeJournalEntry(ObjectId id, String username){
        boolean removed=false;
        try{
            User user=userRepo.findByUsername(username);
            System.out.println(user.getEntryList());
            removed = user.getEntryList().removeIf(entry -> entry.getId().equals(id));
            if(removed){
                journalEntryRepo.deleteById(id);
                userRepo.save(user);
            }
            return removed;
        }catch (Exception e){
            System.out.println(e);
            throw new RuntimeException("an error occured while deleting entry");
        }

    }
    public JournalEntry getById(ObjectId id){

        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        User user=userRepo.findByUsername(username);
        List<JournalEntry> journalEntries=user.getEntryList().stream().filter(entry -> entry.getId().equals(id)).collect(Collectors.toList());
        if(journalEntries!=null && !journalEntries.isEmpty()){
            return journalEntryRepo.findById(id).get();
        }
        return null;

    }
}
