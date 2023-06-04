package com.starer.stock.collection;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Rank {

    @Id
    @Column(updatable = false, unique = true, nullable = false)
    private String id;
    private int stockCount;
    private String stockName;
    private Date buyDate;
    private Date sellDate;
    private int income;

    //Todo 계정 정보
}
