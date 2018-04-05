package com.javaprogrammer.userchatspring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_visit")
public class UserVisit {
    @Id
    @GeneratedValue
    @Column
    private int id;
    @Column(name = "to_id")
    private int toId;
    @ManyToOne
    @NotNull
   private User user;

    public UserVisit(int toId, User user) {
        this.toId = toId;
        this.user = user;
    }
}
