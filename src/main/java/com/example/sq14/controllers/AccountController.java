package com.example.sq14.controllers;

import com.example.sq14.dto.TransferRequest;
import com.example.sq14.model.Account;
import com.example.sq14.service.TransferService;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;

@RestController
public class AccountController {
    private final TransferService transferService;

    public AccountController(TransferService transferService) {
        this.transferService = transferService;
    }

    // конечная точка для перевода денег
    @PostMapping("/transfer")
    public void transferMoney(@RequestBody TransferRequest request)
            throws AccountNotFoundException {
        transferService.transferMoney(
                request.getSenderAccountId(),
                request.getReceiverAccountId(),
                request.getAmount());
    }

    // конечная точка для чтения записей из базы данных
    @GetMapping("/accounts")
    public Iterable<Account> getAllAccounts(
              @RequestParam(required = false) String name) {
        // Если не передадим имя в виде дополнительного параметра,
        // вернется информация обо всех счетах
        if (name == null) {
            return transferService.getAllAccounts();
        } else {
            return transferService.findAccountsByName((name));
        }
    }
}
