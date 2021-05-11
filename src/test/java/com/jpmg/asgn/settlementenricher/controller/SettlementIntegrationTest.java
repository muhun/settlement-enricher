package com.jpmg.asgn.settlementenricher.controller;

import com.jpmg.asgn.settlementenricher.service.SettlementService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@WebMvcTest(SettlementController.class)
public class SettlementIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SettlementService settlementService;


    @Test
    public void testCreateSettlementEndpoint() throws Exception {
        mockMvc.perform( MockMvcRequestBuilders
                .post("/settlement/create")
                .content("{\n" +
                        "  \"tradeId\": \"testing\",\n" +
                        "  \"ssiCode\": \"testing\",\n" +
                        "  \"currency\": \"USD\",\n" +
                        "  \"amount\": \"12334.34\",\n" +
                        "  \"valueDate\": \"12022021\"\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void testCreateSettlementEndpointWithInvalidParam() throws Exception {
        mockMvc.perform( MockMvcRequestBuilders
                .post("/settlement/create")
                .content("{\n" +
                        "  \"tradeId\": \"\",\n" +
                        "  \"ssiCode\": \"testing\",\n" +
                        "  \"currency\": \"USD\",\n" +
                        "  \"amount\": \"12334.34\",\n" +
                        "  \"valueDate\": \"12022021\"\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }
}
