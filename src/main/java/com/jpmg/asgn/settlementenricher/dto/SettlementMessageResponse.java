package com.jpmg.asgn.settlementenricher.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
public class SettlementMessageResponse {
    private UUID messageId;
    private String tradeId;
    private String currency;
    private BigDecimal amount;
    private String valueDate;
    private BankParty payerParty;
    private BankParty receiverParty;
    private String supportingInformation;
}
