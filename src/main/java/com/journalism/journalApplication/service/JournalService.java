package com.journalism.journalApplication.service;

import com.journalism.journalApplication.entity.JournalEntry;
import com.journalism.journalApplication.entity.User;
import com.journalism.journalApplication.repository.JournalEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalService {

    @Autowired
    private JournalEntryRepository journalEntRep;

    @Autowired
    private UserService userService;
    public List<JournalEntry> getAllJournalEntries() {
        return journalEntRep.findAll();
    }
    public void saveEntry(JournalEntry journalEntry, String userName) {
        try {
            User user = userService.findByUserName(userName);
            journalEntry.setDate(LocalDate.from(LocalDateTime.now()));
            JournalEntry saved = journalEntRep.save(journalEntry);
            user.getJournalEntries().add(saved);
            userService.saveEntry(user);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while saving the entry.", e);
        }
    }
    public Optional<JournalEntry> getJournalById(Long id){
        return journalEntRep.findById(id);

    }
    public void deleteEntryById(Long id , String userName) {
        User user = userService.findByUserName(userName);
        user.getJournalEntries().removeIf(x -> x.getId() == id);
        userService.saveEntry(user);
        journalEntRep.deleteById(id);
    }
}
