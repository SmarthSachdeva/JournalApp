package com.journalism.journalApplication.service;

import com.journalism.journalApplication.entity.JournalEntry;
import com.journalism.journalApplication.repository.JournalEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JournalService {

    @Autowired
    private JournalEntryRepository journalEntRep;

    public List<JournalEntry> getAllJournalEntries() {
        return journalEntRep.findAll();
    }
    public void saveEntry(JournalEntry entry) {
        journalEntRep.save(entry);
    }
    public Optional<JournalEntry> getJournalById(Long id){
        return journalEntRep.findById(id);

    }
    public void deleteEntryById(Long id) {
        journalEntRep.deleteById(id);
    }
}
