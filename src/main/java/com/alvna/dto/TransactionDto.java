package com.alvna.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
public record TransactionDto (
     Long transactionId,
     Long operationId,
     LocalDate accountingDate,
     LocalDate valueDate,
     BigDecimal amount,
     String description){
}