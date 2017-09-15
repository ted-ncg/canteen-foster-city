package com.visa.ncg.canteen;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;



@Controller
public class AccountWebController {
    private AccountRepository accountRepository;

    public AccountWebController(AccountRepository accountRepository){
        this.accountRepository = accountRepository;

    }

    @GetMapping("/account/{id}")
    public String accountView(@PathVariable("id") String id, Model model) {
        AccountResponse response = new AccountResponse();
        Account account = accountRepository.findOne(Long.valueOf(id));
        if (account == null) {
            //throw custom exception
        }
        response.setId(account.getId());
        response.setBalance(account.balance());
        response.setName(account.getName());
        model.addAttribute("account", response);

        return "account-view";
    }

    @GetMapping("/account")
    public String allAccountsView(Model model) {
        List<Account> accounts = accountRepository.findAll();
        if (accounts.size() < 1) {
            //throw custom exception
        }
        List<AccountResponse> toReturn = new ArrayList<>(accounts.size());
        for (Account account : accounts) {
            AccountResponse response = new AccountResponse();
            response.setName(account.getName());
            response.setBalance(account.balance());
            response.setId(account.getId());
            toReturn.add(response);
        }
        model.addAttribute("accounts", toReturn);

        return "all-accounts";
    }

//    @PostMapping("/create-account")
    

}