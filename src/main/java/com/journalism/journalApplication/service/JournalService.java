package com.journalism.journalApplication.service;

import com.journalism.journalApplication.entity.JournalEntry;
import com.journalism.journalApplication.entity.User;
import com.journalism.journalApplication.repository.JournalEntryRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class JournalService {

    @Autowired
    private JournalEntryRepository journalEntRep;

    @Autowired
    private UserService userService;
    public List<JournalEntry> getAllJournalEntries() {
        return journalEntRep.findAll();
    }

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName) {
        try {
            log.info("Saving journal entry from user {}" , userName);
            User user = userService.findByUserName(userName);
            journalEntry.setDate(LocalDate.from(LocalDateTime.now()));
            JournalEntry saved = journalEntRep.save(journalEntry);
            user.getJournalEntries().add(saved);
            userService.saveEntry(user);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException("An error occurred while saving the entry.", e);
        }
    }
    public Optional<JournalEntry> getJournalById(Long id){
        log.info("get journal by id {}" , id);
        return journalEntRep.findById(id);

    }
    public void deleteEntryById(Long id , String userName) {
        User user = userService.findByUserName(userName);
        user.getJournalEntries().removeIf(x -> x.getId() == id);
        userService.saveEntry(user);
        journalEntRep.deleteById(id);
    }
}
