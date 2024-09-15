package com.example.back_end.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * JPA 관련해서 데이터 생성/수정 시 자동으로 생성/수정 시간 적용하기 위한 클래스
 * - JPA 엔티티가 본 클래스를 상속받으면 LocalDateTime 형식으로 생성/수정 시간 자동 저장함
 */
@Getter
@MappedSuperclass       // 상속받은 엔티티가 필드도 컬럼으로 인식하도록 함
@EntityListeners(AuditingEntityListener.class)
public class BaseTimeEntity {

    @CreatedDate
    private LocalDateTime createdDateTime;

    @LastModifiedDate
    private LocalDateTime modifiedDateTime;

}
