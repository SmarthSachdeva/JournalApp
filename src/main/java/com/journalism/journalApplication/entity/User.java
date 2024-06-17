package com.journalism.journalApplication.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @NonNull
    private String userName;
    @NonNull
    private String password;

    @OneToMany
    private List<JournalEntry> journalEntries = new ArrayList<>();

}
