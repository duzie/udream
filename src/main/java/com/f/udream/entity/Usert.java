package com.f.udream.entity;

import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@Where(clause = "status = 1")
public class Usert {
    @Id
    @GeneratedValue
    long id;
    int sid;
    String name;
    int status;
}
