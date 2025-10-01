package com.example.quora.models;


import jakarta.persistence.*;
import lombok.*;


@Data
@MappedSuperclass //each entity will get its own table in mysql and Fields (id) are copied into child entities
public class BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
