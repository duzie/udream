package com.f.udream.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Sext {

    @Id
    @GeneratedValue
    int id;
    String name;
}
