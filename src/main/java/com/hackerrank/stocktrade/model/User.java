package com.hackerrank.stocktrade.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@ToString
@Entity(name = "users")
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private Long userId;
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Trade> trades = new ArrayList<>();
    
    public User() {
    }

    public User(Long userId, String name) {
        this.userId = userId;
        this.name = name;
    }
}
