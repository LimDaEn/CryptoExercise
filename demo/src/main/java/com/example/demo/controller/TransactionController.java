package com.example.demo.controller;

import com.example.demo.model.TransactionRequest;
import com.example.demo.model.TransactionResponse;
import com.example.demo.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Resource
    TransactionService transactionService;

    @PostMapping("/execute")
    public TransactionResponse executeTransaction(@Valid @RequestBody TransactionRequest requestBody){
        return transactionService.executeTransaction(requestBody);
    }

    @GetMapping("/retrieve")
    public List<TransactionResponse> retrieveTransaction(@Valid @RequestParam @NotNull Integer userid){
        return transactionService.getTransaction(userid);
    }


}
