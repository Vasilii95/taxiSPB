package com.digitaldesignuniver.server.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "customer_table")
@Data
public class Customer {
    public Customer(){}
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "customer")
//    @JoinTable(name="user_role", joinColumns = @JoinColumn(name="user_id"), inverseJoinColumns = @JoinColumn(name="role_id"))
    private Set<Order> orders;
}
