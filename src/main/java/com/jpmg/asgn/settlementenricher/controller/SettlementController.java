package com.jpmg.asgn.settlementenricher.controller;

import com.jpmg.asgn.settlementenricher.dto.Settlement;
import com.jpmg.asgn.settlementenricher.dto.SettlementMessageResponse;
import com.jpmg.asgn.settlementenricher.service.SettlementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/settlement")
public class SettlementController {

    @Autowired
    SettlementService settlementService;

    @PostMapping("/create")
    public SettlementMessageResponse createSettlement(@RequestBody @Valid Settlement settlement){
        return settlementService.createSettlement(settlement);
    }

    @GetMapping("/{tradeId}")
    public SettlementMessageResponse getSettlement(@PathVariable String tradeId){
        return settlementService.getSettlement(tradeId);
    }
}
