package com.journalism.journalApplication.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Entity
@Table(name = "\"user\"") // Escape the table name
@Data
public class User {
    @Id
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_name", unique = true)
    @NonNull
    private String userName;

    @NonNull
    private String password;

    @OneToMany(mappedBy = "user")
    private List<JournalEntry> journals;
}
