package com.starter.stock.repository;

import com.starter.stock.domain.Record;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface RecordRepository extends JpaRepository<Record, Long> {
    public List<Record> findByStockNameAndBuyDateAndSellDate(String stockName, Date buyDate,  Date sellDate);
}
