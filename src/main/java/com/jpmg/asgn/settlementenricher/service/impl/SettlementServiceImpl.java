package com.jpmg.asgn.settlementenricher.service.impl;

import com.jpmg.asgn.settlementenricher.dto.Settlement;
import com.jpmg.asgn.settlementenricher.dto.SettlementMessageResponse;
import com.jpmg.asgn.settlementenricher.exception.APIServiceException;
import com.jpmg.asgn.settlementenricher.mapper.SettlementMessageMapper;
import com.jpmg.asgn.settlementenricher.mapper.SettlementResponseMapper;
import com.jpmg.asgn.settlementenricher.model.SettlementMessage;
import com.jpmg.asgn.settlementenricher.repo.SettlementMessageRepo;
import com.jpmg.asgn.settlementenricher.service.SettlementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.UUID;

@Service
public class SettlementServiceImpl implements SettlementService {

    @Autowired
    SettlementMessageMapper settlementMessageMapper;

    @Autowired
    SettlementMessageRepo settlementMessageRepo;

    @Autowired
    SettlementResponseMapper settlementResponseMapper;

    @Override
    public SettlementMessageResponse createSettlement(Settlement settlement) {
        SettlementMessage message = settlementMessageMapper.map(settlement, SettlementMessage.class);
        Optional<SettlementMessage> existSettlementMessage = Optional.ofNullable(
                settlementMessageRepo.findFirstByTradeId(settlement.getTradeId()));
        if(!existSettlementMessage.isPresent()){
            try {
                message = settlementMessageRepo.saveAndFlush(message);
            }catch (Exception ex){
                throw new APIServiceException("SSI code constraint error",
                        HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return settlementResponseMapper.map(message, SettlementMessageResponse.class);
        }
        throw new APIServiceException("Duplicate Entry", HttpStatus.CONFLICT);
    }

    @Override
    public SettlementMessageResponse getSettlement(String tradeId) {
        Optional<SettlementMessage> existSettlementMessage = Optional.ofNullable(
                settlementMessageRepo.findFirstByTradeId(tradeId));
        if(existSettlementMessage.isPresent()){
            return settlementResponseMapper.map(existSettlementMessage.get(), SettlementMessageResponse.class);
        }
       throw new APIServiceException("Entity/Resource not found", HttpStatus.NOT_FOUND);
    }
}
