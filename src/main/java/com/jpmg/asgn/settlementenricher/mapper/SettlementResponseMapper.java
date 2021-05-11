package com.jpmg.asgn.settlementenricher.mapper;

import com.jpmg.asgn.settlementenricher.dto.Settlement;
import com.jpmg.asgn.settlementenricher.dto.SettlementMessageResponse;
import com.jpmg.asgn.settlementenricher.model.SettlementMessage;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;


@Component
public class SettlementResponseMapper extends ConfigurableMapper {
    @Override
    protected void configure(MapperFactory factory) {
        factory.classMap(SettlementMessage.class, SettlementMessageResponse.class)
                .field("ssiInfo.payerAccountNumber", "payerParty.accountNumber")
                .field("ssiInfo.payerBank", "payerParty.bankCode")
                .field("ssiInfo.receiverAccountNumber", "receiverParty.accountNumber")
                .field("ssiInfo.receiverBank", "receiverParty.bankCode")
                .field("ssiInfo.supportingInfo", "supportingInformation")
                .customize(new CustomMapper<SettlementMessage, SettlementMessageResponse>() {
                    @Override
                    public void mapAtoB(SettlementMessage settlementMessage,
                                        SettlementMessageResponse settlementMessageResponse,
                                        MappingContext context) {
                        settlementMessageResponse.setValueDate(new SimpleDateFormat("ddMMyyyy")
                                .format(settlementMessage.getValueDate()));
                    }
                })
                .byDefault()
                .exclude("valueDate")
                .register();
    }
}
