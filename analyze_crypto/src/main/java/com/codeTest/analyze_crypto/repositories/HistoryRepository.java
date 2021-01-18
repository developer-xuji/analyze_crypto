package com.codeTest.analyze_crypto.repositories;

import com.codeTest.analyze_crypto.entities.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HistoryRepository extends JpaRepository<History, Long> {
    @Query("select h from History h where h.currency = :currency")
    List<History> findHistoryByCurrency(@Param("currency") String currency);

    @Query("select h.currency from History h group by h.currency")
    List<String> findAllCurrencyType();
}
