package com.jpmg.asgn.settlementenricher.mapper;

import com.jpmg.asgn.settlementenricher.dto.Settlement;
import com.jpmg.asgn.settlementenricher.model.SettlementMessage;
import lombok.SneakyThrows;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
public class SettlementMessageMapper extends ConfigurableMapper {
    @Override
    protected void configure(MapperFactory factory) {
        factory.classMap(Settlement.class, SettlementMessage.class)
                .field("ssiCode", "ssiInfo.ssiCode")
                .customize(new CustomMapper<Settlement, SettlementMessage>() {
                    @Override
                    public void mapAtoB(Settlement a, SettlementMessage b, MappingContext context) {
                        b.setMessageId(UUID.randomUUID());
                    }
                })
                .byDefault()
                .register();
    }
}
