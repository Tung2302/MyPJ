package com.BikeStoreApi.BikeStoreApi.entities;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50, unique = true)
    private String name;

    public Role() { }

    public Role(String name) {
        this.name = name;
    }

    public Role(Integer id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return this.name;
    }


}