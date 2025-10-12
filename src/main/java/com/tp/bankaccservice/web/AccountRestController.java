package com.tp.bankaccservice.web;

import com.tp.bankaccservice.dto.BankAccountRequestDTO;
import com.tp.bankaccservice.dto.BankAccountResponseDTO;
import com.tp.bankaccservice.entities.BankAccount;
import com.tp.bankaccservice.mappers.AccountMapper;
import com.tp.bankaccservice.repositories.BankAccountRepository;
import com.tp.bankaccservice.services.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class AccountRestController {
    private BankAccountRepository bankAccountRepository;
    private AccountService accountService;
    AccountMapper accountMapper;
    @GetMapping("/bankAccounts")
    public List<BankAccount> bankAccounts(){
        return bankAccountRepository.findAll();
    }

    @GetMapping("/bankAccounts/{id}")
    public BankAccount bankAccount(@PathVariable String id){
        return bankAccountRepository.findById(id)
                .orElseThrow(()-> new RuntimeException(String.format("Account not found")));
    }
    @PostMapping("/bankAccounts")
    public BankAccountResponseDTO save(@RequestBody BankAccountRequestDTO account){
        return accountService.addAccount(account);
    }
    @PutMapping("/bankAccounts/{i}")
    public BankAccount update(@PathVariable String i , @RequestBody BankAccount account){
        BankAccount bankAccount = bankAccount(i);
        if (account.getBalance()!=null) bankAccount.setBalance(account.getBalance());
        if (account.getType()!=null) bankAccount.setType(account.getType());
        if (account.getCurrency()!=null) bankAccount.setCurrency(account.getCurrency());
        if (account.getCreatedAt()!=null) bankAccount.setCreatedAt(account.getCreatedAt());
        return bankAccountRepository.save(bankAccount);
    }


    @DeleteMapping("/bankAccounts/{i}")
    public void delete(@PathVariable String i){
        bankAccountRepository.deleteById(i);
    }
}
