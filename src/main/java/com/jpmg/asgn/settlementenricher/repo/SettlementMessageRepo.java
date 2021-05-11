package com.jpmg.asgn.settlementenricher.repo;

import com.jpmg.asgn.settlementenricher.model.SettlementMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SettlementMessageRepo extends JpaRepository<SettlementMessage, Long> {
    SettlementMessage findFirstByTradeId(String tradeId);
}
