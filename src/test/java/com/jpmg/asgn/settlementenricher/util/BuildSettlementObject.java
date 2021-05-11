package com.jpmg.asgn.settlementenricher.util;

import com.jpmg.asgn.settlementenricher.dto.BankParty;
import com.jpmg.asgn.settlementenricher.dto.Settlement;
import com.jpmg.asgn.settlementenricher.dto.SettlementMessageResponse;
import com.jpmg.asgn.settlementenricher.model.SSIInfo;
import com.jpmg.asgn.settlementenricher.model.SettlementMessage;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.UUID;

public class BuildSettlementObject {

    public static final String testing = "testing";

    public static Settlement buildSettlement(){
        Settlement settlement =  new Settlement();
        settlement.setAmount(new BigDecimal(123));
        settlement.setCurrency("tst");
        settlement.setSsiCode(testing);
        settlement.setTradeId(testing);
        settlement.setValueDate(new Date(System.currentTimeMillis()));
        return settlement;
    }

    public static SettlementMessage buildSettlementMessage(){
        SettlementMessage sm = new SettlementMessage();
        sm.setTradeId("testing");
        sm.setCurrency("tst");
        sm.setAmount(new BigDecimal(123));
        sm.setValueDate(new Date(System.currentTimeMillis()));
        sm.setMessageId(UUID.randomUUID());

        SSIInfo ssiInfo = new SSIInfo();
        ssiInfo.setPayerAccountNumber("testing-01");
        ssiInfo.setPayerBank("testingbank");
        ssiInfo.setReceiverAccountNumber("testing-02");
        ssiInfo.setReceiverBank("testingbank2");
        ssiInfo.setSsiCode("testing");
        ssiInfo.setSupportingInfo("testinginfo");

        sm.setSsiInfo(ssiInfo);
        return sm;
    }

    public static SettlementMessageResponse buildSettlementMessageResponse(){
        SettlementMessageResponse resp = new SettlementMessageResponse();
        resp.setAmount(new BigDecimal(123));
        resp.setCurrency("tst");
        resp.setMessageId(UUID.randomUUID());
        resp.setTradeId(testing);
        resp.setValueDate(new SimpleDateFormat("ddMMyyyy")
                .format(new Date(System.currentTimeMillis())));

        BankParty payer = new BankParty();
        payer.setAccountNumber("testing-01");
        payer.setBankCode("testingbank");

        BankParty receiver = new BankParty();
        receiver.setAccountNumber("testing-02");
        receiver.setBankCode("testingbank2");
        resp.setPayerParty(payer);
        resp.setReceiverParty(receiver);
        return resp;
    }
}
