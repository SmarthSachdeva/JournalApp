package com.journalism.journalApplication.controller;

import com.journalism.journalApplication.entity.JournalEntry;
import com.journalism.journalApplication.entity.User;
import com.journalism.journalApplication.service.JournalService;
import com.journalism.journalApplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalControllerV2 {

    @Autowired
    private UserService userService;
    @Autowired
    private JournalService journalService;

   @GetMapping("/all/{username}")
    public ResponseEntity<?> getAllJournalsOfUser(@PathVariable String username) {
        User user = userService.findByUserName(username);
        List<JournalEntry> entries = user.getJournalEntries();
        if(entries!=null && !entries.isEmpty()){
            return new ResponseEntity<>(entries , HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @GetMapping("/{tempId}")
    public ResponseEntity<JournalEntry> getJournalById(@PathVariable Long tempId) {
        Optional<JournalEntry> journalEntry = journalService.getJournalById(tempId);
        if(journalEntry.isPresent()){
            return new ResponseEntity<>(journalEntry.get() , HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(null , HttpStatus.NOT_FOUND);
        }

    }

   @PostMapping("/create/{userName}")
    public ResponseEntity<JournalEntry> create(@RequestBody JournalEntry entry ,
                                               @PathVariable String userName ) {
       try{
           journalService.saveEntry(entry , userName);
           return new ResponseEntity<>(entry , HttpStatus.CREATED);
       }
       catch(Exception e){
           return new ResponseEntity<>(null , HttpStatus.NOT_FOUND);
       }
    }

    @DeleteMapping("/del/{tempId}")
    public ResponseEntity<JournalEntry> deleteJournal(@PathVariable Long tempId , @PathVariable String userName) {
        try{
            Optional<JournalEntry> deleted = journalService.getJournalById(tempId);
            journalService.deleteEntryById(tempId , userName);
            return new ResponseEntity<>(deleted.get() , HttpStatus.NO_CONTENT);
        }
        catch(Exception e){
            return new ResponseEntity<>(null , HttpStatus.NOT_FOUND);
        }
    }

}

