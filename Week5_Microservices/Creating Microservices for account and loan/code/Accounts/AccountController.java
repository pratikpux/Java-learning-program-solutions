package com.cognizant.account.controller;

import com.cognizant.account.model.Account;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @GetMapping("/accounts/{number}")
    public Account getAccountDetails(@PathVariable String number) {
        // Dummy response without backend connectivity
        Account account = new Account();
        account.setNumber(number);
        account.setType("savings");
        account.setBalance(234343);
        return account;
    }
}