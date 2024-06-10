package com.journalism.journalApplication.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDate;

@Entity
@NamedEntityGraphs({
        @NamedEntityGraph(name = "JournalEntry.user", attributeNodes = @NamedAttributeNode("user"))
})
@Data
public class JournalEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    private String title;
    private String content;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}
