package com.digitaldesignuniver.server.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "dispatcher_table")
@Data
public class Dispatcher {
    public Dispatcher(){}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "name")
    private String name;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "dispatcher")
    private Set<Order> orders;
}
