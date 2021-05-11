package com.jpmg.asgn.settlementenricher.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "ssi_info")
public class SSIInfo {
    @Id
    private String ssiCode;
    private String payerAccountNumber;
    private String payerBank;
    private String receiverAccountNumber;
    private String receiverBank;
    private String supportingInfo;

    @OneToMany
    @JoinColumn(name = "ssiCode", insertable=false, updatable=false)
    private List<SettlementMessage> settlementMessages;
}
