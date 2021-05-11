package com.jpmg.asgn.settlementenricher.service;

import com.jpmg.asgn.settlementenricher.dto.Settlement;
import com.jpmg.asgn.settlementenricher.dto.SettlementMessageResponse;
import com.jpmg.asgn.settlementenricher.exception.APIServiceException;
import com.jpmg.asgn.settlementenricher.mapper.SettlementMessageMapper;
import com.jpmg.asgn.settlementenricher.mapper.SettlementResponseMapper;
import com.jpmg.asgn.settlementenricher.model.SettlementMessage;
import com.jpmg.asgn.settlementenricher.repo.SettlementMessageRepo;
import com.jpmg.asgn.settlementenricher.service.impl.SettlementServiceImpl;
import com.jpmg.asgn.settlementenricher.util.BuildSettlementObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class SettlementServiceImplTest {

    @Mock
    SettlementMessageMapper settlementMessageMapper;

    @Mock
    SettlementMessageRepo settlementMessageRepo;

    @Mock
    SettlementResponseMapper settlementResponseMapper;

    @InjectMocks
    SettlementServiceImpl settlementService;


    @Test
    public void testCreateSettlementMessage(){
        //given
        Settlement settlement = BuildSettlementObject.buildSettlement();
        SettlementMessage settlementMessage = BuildSettlementObject.buildSettlementMessage();
        SettlementMessageResponse response = BuildSettlementObject.buildSettlementMessageResponse();
        //when
        when(settlementMessageMapper.map(settlement, SettlementMessage.class)).thenReturn(settlementMessage);
        when(settlementMessageRepo.findFirstByTradeId(settlement.getTradeId())).thenReturn(null);
        when(settlementMessageRepo.saveAndFlush(settlementMessage)).thenReturn(settlementMessage);
        when(settlementResponseMapper.map(settlementMessage, SettlementMessageResponse.class))
                .thenReturn(response);

        SettlementMessageResponse result = settlementService.createSettlement(settlement);
        //assert
        assertNotNull(result);
        assertEquals(result.getTradeId(), settlementMessage.getTradeId());
        assertEquals(result.getPayerParty().getAccountNumber(), settlementMessage.getSsiInfo().getPayerAccountNumber());
    }

    @Test
    public void testCreateSettlementMessageWithExistingTradeId(){
        //given
        Settlement settlement = BuildSettlementObject.buildSettlement();
        SettlementMessage settlementMessage = BuildSettlementObject.buildSettlementMessage();
        SettlementMessageResponse response = BuildSettlementObject.buildSettlementMessageResponse();
        //when
        when(settlementMessageMapper.map(settlement, SettlementMessage.class)).thenReturn(settlementMessage);
        when(settlementMessageRepo.findFirstByTradeId(settlement.getTradeId())).thenReturn(settlementMessage);

        //assert
        Throwable exception = assertThrows(APIServiceException.class,
                ()->settlementService.createSettlement(settlement));
        assertTrue(exception.getMessage().contains("Duplicate Entry"));

    }

    @Test
    public void testCreateSettlementMessageWithInvalidSSICode(){
        //given
        Settlement settlement = BuildSettlementObject.buildSettlement();
        SettlementMessage settlementMessage = BuildSettlementObject.buildSettlementMessage();
        SettlementMessageResponse response = BuildSettlementObject.buildSettlementMessageResponse();
        //when
        when(settlementMessageMapper.map(settlement, SettlementMessage.class)).thenReturn(settlementMessage);
        when(settlementMessageRepo.findFirstByTradeId(settlement.getTradeId())).thenReturn(null);
        when(settlementMessageRepo.saveAndFlush(settlementMessage)).thenThrow(DataIntegrityViolationException.class);

        //assert
        Throwable exception = assertThrows(APIServiceException.class,
                ()->settlementService.createSettlement(settlement));
        assertTrue(exception.getMessage().contains("SSI code constraint"));
    }

    @Test
    public void testGetSettlementMessage(){
        //given
        SettlementMessage settlementMessage = BuildSettlementObject.buildSettlementMessage();
        SettlementMessageResponse response = BuildSettlementObject.buildSettlementMessageResponse();
        //when
        when(settlementMessageRepo.findFirstByTradeId("testing")).thenReturn(settlementMessage);
        when(settlementResponseMapper.map(settlementMessage, SettlementMessageResponse.class))
                .thenReturn(response);

        SettlementMessageResponse result = settlementService.getSettlement("testing");
        //assert
        assertNotNull(result);
        assertEquals(result.getTradeId(), settlementMessage.getTradeId());
        assertEquals(result.getPayerParty().getAccountNumber(), settlementMessage.getSsiInfo().getPayerAccountNumber());
    }

    @Test
    public void testGetSettlementMessageNotAvailable(){
        //given
        SettlementMessage settlementMessage = BuildSettlementObject.buildSettlementMessage();
        SettlementMessageResponse response = BuildSettlementObject.buildSettlementMessageResponse();
        //when
        when(settlementMessageRepo.findFirstByTradeId("testing")).thenReturn(null);

        Throwable exception = assertThrows(APIServiceException.class,
                ()->settlementService.getSettlement("testing"));
        assertTrue(exception.getMessage().contains("not found"));
    }


}
