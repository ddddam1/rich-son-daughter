package com.starter.stock.repository;

import com.starter.stock.domain.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RankingRepository extends JpaRepository<Ranking, Long> {
}
