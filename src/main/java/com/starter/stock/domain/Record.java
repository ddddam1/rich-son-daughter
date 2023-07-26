package com.starter.stock.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

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

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date buyDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date sellDate;
    private int income;

}
