package com.f.udream.entity;

import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Immutable
@Subselect("select u.id user_id,u.name,s.name sex_name from usert u , sext s where u.sid = s.id")
@Data
public class UserInfo {
    @Id
    int userId;
    String sexName;
    String name;
}
