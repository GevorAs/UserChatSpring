package com.javaprogrammer.userchatspring.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "request")
public class Request {

    @Id
    @Column
    @GeneratedValue
    private int id;
    @ManyToOne
    private User fromId;
    @ManyToOne
    private User toId;
}
