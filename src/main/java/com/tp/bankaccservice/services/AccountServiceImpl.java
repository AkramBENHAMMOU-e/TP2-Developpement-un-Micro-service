package com.tp.bankaccservice.services;

import com.tp.bankaccservice.dto.BankAccountRequestDTO;
import com.tp.bankaccservice.dto.BankAccountResponseDTO;
import com.tp.bankaccservice.entities.BankAccount;
import com.tp.bankaccservice.mappers.AccountMapper;
import com.tp.bankaccservice.repositories.BankAccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @Transactional @AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    BankAccountRepository bankAccountRepository;
    AccountMapper accountMapper;
    @Override
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO) {
        BankAccount bankAccount = accountMapper.fromBankAccountRequestDTO(bankAccountDTO);
        BankAccount saved = bankAccountRepository.save(bankAccount);
       BankAccountResponseDTO bankAccountResponseDTO = accountMapper.fromBankAccount(saved);
        return bankAccountResponseDTO ;
    }

    @Override
    public BankAccountResponseDTO updateAccount(String id, BankAccountRequestDTO bankAccountDTO) {
        BankAccount bankAccount = bankAccountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
        if (bankAccountDTO.getBalance() != null) bankAccount.setBalance(bankAccountDTO.getBalance());
        if (bankAccountDTO.getType() != null) bankAccount.setType(bankAccountDTO.getType());
        if (bankAccountDTO.getCurrency() != null) bankAccount.setCurrency(bankAccountDTO.getCurrency());
        BankAccount saved = bankAccountRepository.save(bankAccount);
        return accountMapper.fromBankAccount(saved);
    }

    @Override
    public void deleteAccount(String id) {
        bankAccountRepository.deleteById(id);
    }
}
