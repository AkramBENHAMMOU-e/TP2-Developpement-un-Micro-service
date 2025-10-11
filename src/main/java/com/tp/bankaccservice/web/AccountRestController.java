package com.tp.bankaccservice.web;

import com.tp.bankaccservice.entities.BankAccount;
import com.tp.bankaccservice.repositories.BankAccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class AccountRestController {
    private BankAccountRepository bankAccountRepository;

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
    public BankAccount save(@RequestBody BankAccount account){
        if (account.getId()==null) account.setId(UUID.randomUUID().toString());
        return bankAccountRepository.save(account);
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
