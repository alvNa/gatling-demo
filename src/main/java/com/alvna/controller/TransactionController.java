package com.alvna.controller;

import com.alvna.dto.TransactionDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static com.alvna.controller.TransactionController.ACCOUNT_BASE;

@RequiredArgsConstructor
@RestController
//@RequestMapping(ACCOUNT_BASE)
@Slf4j
public class TransactionController {
    public static final String ACCOUNT_BASE = "/";
    public static final String TRANSACTIONS_PATH = "/accounts/{accountId}/transactions";
    public static final String FROM_DATE_QUERY_PARAM = "fromAccountingDate";
    public static final String TO_DATE_QUERY_PARAM = "toAccountingDate";

    //private final AccountTransactionService accountTransactionService;
    private final AtomicInteger counter = new AtomicInteger();

    @GetMapping(TRANSACTIONS_PATH)
    public ResponseEntity<List<TransactionDto>> getTransactions(@PathVariable Long accountId) throws InterruptedException {
        int index = counter.incrementAndGet();
        log.info("getTrans {}",index);
        if (index % 50 == 0){
            Thread.sleep(200);
        }
        List<TransactionDto> transactions = List.of(); //accountTransactionService.getTransactions(accountId)
        return ResponseEntity.ok(transactions);
    }
}