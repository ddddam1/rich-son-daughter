package com.starter.stock.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Record {

    @Id @GeneratedValue
    @Column(name = "record_id")
    private Long id;

    @OneToMany(mappedBy = "record")
    private List<Ranking> ranking;

    private int stockCount;
    private String stockName;
    private Date buyDate;
    private Date sellDate;
    private int income;

}
