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

    @GetMapping("/{username}")
    public ResponseEntity<?> getAllJournalsOfUser(@PathVariable String username) {
        User user = userService.findByUserName(username);
        // List<JournalEntry> entriesSortedByUser = new ArrayList<>();
        List<JournalEntry> entries = user.getJournals();
        if(entries!=null){
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

    @PostMapping("/create")
    public ResponseEntity<JournalEntry> create(@RequestBody JournalEntry entry) {
       try{
           entry.setDate(LocalDate.now());
           journalService.saveEntry(entry);
           return new ResponseEntity<>(entry , HttpStatus.CREATED);
       }
       catch(Exception e){
           return new ResponseEntity<>(null , HttpStatus.NOT_FOUND);
       }
    }

    @DeleteMapping("/del/{tempId}")
    public ResponseEntity<JournalEntry> deleteJournal(@PathVariable Long tempId) {
        try{
            Optional<JournalEntry> deleted = journalService.getJournalById(tempId);
            journalService.deleteEntryById(tempId);
            return new ResponseEntity<>(deleted.get() , HttpStatus.NO_CONTENT);
        }
        catch(Exception e){
            return new ResponseEntity<>(null , HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{tempId}")
    public ResponseEntity<Boolean> updateJournal(@PathVariable Long tempId, @RequestBody JournalEntry j) {
        JournalEntry je = journalService.getJournalById(tempId).orElse(null);
        try{
            je.setTitle(j.getTitle()!=null && !j.getTitle().isEmpty() ? je.getTitle() : j.getTitle());
            je.setContent(j.getContent()!=null && !j.getContent().isEmpty() ? je.getContent() : j.getContent());
            journalService.saveEntry(je);
            return new ResponseEntity<>(true , HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(false , HttpStatus.NOT_FOUND);
        }
    }
}

