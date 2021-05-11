package com.jpmg.asgn.settlementenricher.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.OptBoolean;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Component;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

@Getter
@Setter
@Component
public class Settlement {

    @NotBlank(message = "trade id is required")
    private String tradeId;
    @NotBlank(message = "ssi code is required")
    private String ssiCode;
    @NotBlank(message = "currency is required")
    @Size(min = 3, max = 3, message = "char length must be 3")
    private String currency;
    @DecimalMin("0.0")
    private BigDecimal amount;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern="ddMMyyyy", lenient = OptBoolean.FALSE)
    @Temporal(TemporalType.DATE)
    private Date valueDate;
}
