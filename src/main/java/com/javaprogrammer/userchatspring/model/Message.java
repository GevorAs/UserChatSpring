package com.javaprogrammer.userchatspring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue
    @Column
    private int id;
    @Column
    private String text;
    @Column(name = "from_id")
    private int fromId;
    @Column(name = "to_id")
    private int toId;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private MessageStatus messageStatus;
    @Column(name = "send_picture")
    private String picture;
    @Column(name = "send_file")
    private String file;
    @Column
    private String timestamp;
}
