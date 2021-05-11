package com.jpmg.asgn.settlementenricher.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "settlement_message")
public class SettlementMessage {

    @Id
    private UUID messageId;
    private String tradeId;
    private String currency;
    private BigDecimal amount;
    private Date valueDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ssiCode")
    private SSIInfo ssiInfo;
}
