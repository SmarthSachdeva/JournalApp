package com.journalism.journalApplication.repository;
//import com.journalism.journalApplication.
import com.journalism.journalApplication.entity.JournalEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JournalEntryRepository extends JpaRepository<JournalEntry, Long> {

}
