package com.starter.stock.repository;

import com.starter.stock.domain.Ranking;
import com.starter.stock.domain.Record;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface RankingRepository extends JpaRepository<Ranking, Long> {
}
