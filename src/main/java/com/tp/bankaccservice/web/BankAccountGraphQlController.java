package com.tp.bankaccservice.web;

import com.tp.bankaccservice.dto.BankAccountRequestDTO;
import com.tp.bankaccservice.dto.BankAccountResponseDTO;
import com.tp.bankaccservice.entities.BankAccount;
import com.tp.bankaccservice.enums.AccountType;
import com.tp.bankaccservice.repositories.BankAccountRepository;
import com.tp.bankaccservice.services.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class BankAccountGraphQlController {

    private BankAccountRepository bankAccountRepository;
    private AccountService accountService;

    @QueryMapping
    public List<BankAccount> accountList(){

        return bankAccountRepository.findAll();
    }
    @QueryMapping
    public BankAccount bankAccountById(@Argument String id){

        return bankAccountRepository.findById(id).orElse(null);
    }

    @QueryMapping
    public List<BankAccount> accountsByType(@Argument AccountType type){
        return bankAccountRepository.findByType(type);
    }

    @MutationMapping
    public BankAccountResponseDTO addAccount(@Argument BankAccountRequestDTO bankAccount){
        return accountService.addAccount(bankAccount);
    }

    @MutationMapping
    public BankAccountResponseDTO updateAccount(@Argument String id, @Argument BankAccountRequestDTO bankAccount){
        return accountService.updateAccount(id, bankAccount);
    }

    @MutationMapping
    public Boolean deleteAccount(@Argument String id){
        accountService.deleteAccount(id);
        return true;
    }
}
