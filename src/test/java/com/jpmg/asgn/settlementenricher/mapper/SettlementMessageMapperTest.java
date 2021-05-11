package com.jpmg.asgn.settlementenricher.mapper;

import com.jpmg.asgn.settlementenricher.dto.Settlement;
import com.jpmg.asgn.settlementenricher.model.SettlementMessage;
import com.jpmg.asgn.settlementenricher.util.BuildSettlementObject;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@SpringBootTest
public class SettlementMessageMapperTest {

    @InjectMocks
    SettlementMessageMapper settlementMessageMapper;

    @Test
    public void testSettlementMessageMapping(){
        Settlement settlement = BuildSettlementObject.buildSettlement();
        SettlementMessage settlementMessage = settlementMessageMapper.map(settlement,
                SettlementMessage.class);
        assertEquals(settlement.getTradeId(), settlementMessage.getTradeId());
        assertEquals(settlement.getSsiCode(), settlementMessage.getSsiInfo().getSsiCode());
        assertEquals(settlement.getAmount(), settlementMessage.getAmount());
        assertEquals(settlement.getCurrency(), settlementMessage.getCurrency());
        assertEquals(settlement.getValueDate(), settlementMessage.getValueDate());
        assertNotNull(settlementMessage.getMessageId());

    }
}
