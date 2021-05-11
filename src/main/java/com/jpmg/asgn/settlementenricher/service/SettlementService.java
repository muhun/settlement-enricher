package com.jpmg.asgn.settlementenricher.service;

import com.jpmg.asgn.settlementenricher.dto.Settlement;
import com.jpmg.asgn.settlementenricher.dto.SettlementMessageResponse;
import com.jpmg.asgn.settlementenricher.model.SettlementMessage;
import org.springframework.stereotype.Service;


public interface SettlementService {

    public SettlementMessageResponse createSettlement(Settlement settlement);
    public SettlementMessageResponse getSettlement(String tradeId);

}
