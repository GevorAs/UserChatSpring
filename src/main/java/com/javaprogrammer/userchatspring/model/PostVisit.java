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
@Table(name = "post_visit")
public class PostVisit {
    @Id
    @GeneratedValue
    @Column
    private int id;
    @ManyToOne
    @NotNull
    private Post post;


    public PostVisit(Post post) {
        this.post = post;
    }
}
