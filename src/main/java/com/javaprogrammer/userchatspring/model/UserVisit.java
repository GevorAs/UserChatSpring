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
    @ManyToOne
    @NotNull
   private User user;

    public UserVisit(User user) {
        this.user = user;
    }
}
