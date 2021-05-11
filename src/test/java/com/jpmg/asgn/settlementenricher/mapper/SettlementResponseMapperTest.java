package com.jpmg.asgn.settlementenricher.mapper;

import com.jpmg.asgn.settlementenricher.dto.Settlement;
import com.jpmg.asgn.settlementenricher.dto.SettlementMessageResponse;
import com.jpmg.asgn.settlementenricher.model.SettlementMessage;
import com.jpmg.asgn.settlementenricher.util.BuildSettlementObject;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class SettlementResponseMapperTest {

    @InjectMocks
    SettlementResponseMapper settlementResponseMapper;

    @Test
    public void testSettlementMessageMapping(){
        SettlementMessage settlementMessage = BuildSettlementObject.buildSettlementMessage();
        SettlementMessageResponse settlementMessageResponse = settlementResponseMapper.map(settlementMessage,
                SettlementMessageResponse.class);
        assertEquals(settlementMessage.getTradeId(), settlementMessageResponse.getTradeId());
        assertEquals(settlementMessage.getSsiInfo().getPayerAccountNumber(),
                settlementMessageResponse.getPayerParty().getAccountNumber());
        assertEquals(settlementMessage.getSsiInfo().getReceiverAccountNumber(),
                settlementMessageResponse.getReceiverParty().getAccountNumber());
        assertEquals(settlementMessage.getSsiInfo().getPayerBank(),
                settlementMessageResponse.getPayerParty().getBankCode());
        assertEquals(settlementMessage.getSsiInfo().getReceiverBank(),
                settlementMessageResponse.getReceiverParty().getBankCode());

        assertEquals(settlementMessage.getAmount(), settlementMessageResponse.getAmount());
        assertEquals(settlementMessage.getCurrency(), settlementMessageResponse.getCurrency());
        assertEquals(new SimpleDateFormat("ddMMyyyy")
                .format(settlementMessage.getValueDate()), settlementMessageResponse.getValueDate());
        assertNotNull(settlementMessageResponse.getMessageId());
    }
}
