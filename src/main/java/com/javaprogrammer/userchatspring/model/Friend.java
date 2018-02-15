package com.javaprogrammer.userchatspring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "friend")
public class Friend {
    @Id
    @Column
    @GeneratedValue
    private int id;
    @ManyToOne
    private User userId;
    @ManyToOne
    private User friendId;
}
