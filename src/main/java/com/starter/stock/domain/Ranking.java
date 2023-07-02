package com.starter.stock.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Ranking {

    @Id @GeneratedValue
    @Column(name = "ranking_id")
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "record_id")
    private Record record;

    @CreationTimestamp
    private Date recordDate;

    //Todo 소셜 로그인 인증 여부
}
