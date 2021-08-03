package com.hello.servlet.domain.member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Member {

    private Long id; // DB에 저장하면 ID가 발급되는형식
    private String username;
    private int age;

    public Member(){
    }

    public Member(String username, int age){
        this.username = username;
        this.age = age;
    }

}
