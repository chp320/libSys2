package com.example.back_end.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 회원 정보를 관리한다.
 * - 아이디(id), 이름(name), 생년월일(birth), 성별(sex)
 * - BaseTimeEntity 를 상속받아 생성/수정 시간을 자동 저장
 */
@Getter
@Setter
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(nullable = false, unique = true)
    private String userID;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String userBirth;

    private String sex;

}
