package com.CRUD.test.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Entity
@NoArgsConstructor
public class User {
    @Id @GeneratedValue
    private Long idx;
    private String id;
    private String pw;

    @Builder
    User(Long idx, String id, String pw){
        this.idx = idx;
        this.id = id;
        this.pw = pw;
    }
    public void update(String id, String pw){
        this.id = id;
        this.pw = pw;
    }
}
