package com.journalism.journalApplication.controller;

import com.journalism.journalApplication.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/_journal")
public class JournalController {

    private Map<Long , JournalEntry> journalEntries = new HashMap();

    @GetMapping("/getall")
    public List<JournalEntry> getAll() {
        return new ArrayList<>(journalEntries.values());
    }
    @GetMapping("/getall/{tempId}")
    public JournalEntry getJournalById(@PathVariable Long tempId) {
        return journalEntries.get(tempId);
    }
    @PostMapping("/create")
    public Boolean create(@RequestBody JournalEntry entry) {
        if(entry!=null) {
            journalEntries.put(entry.getId(), entry);
            return true;
        }
        return false;
    }
    @DeleteMapping("/del/{tempId}")
    public JournalEntry deleteJournal(@PathVariable Long tempId){
        return journalEntries.remove(tempId);
    }

    @PutMapping("/update/{tempId}")
    public boolean updateJournal(@PathVariable Long tempId , @RequestBody JournalEntry j){
        journalEntries.put(tempId , j);
        return true;
    }
}

