package com.starer.stock.repository;

import com.starer.stock.collection.Member;
import com.starer.stock.collection.Rank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RankRepository extends JpaRepository<Rank, Long> {
}
