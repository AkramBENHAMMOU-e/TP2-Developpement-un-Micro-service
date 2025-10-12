package com.tp.bankaccservice.services;

import com.tp.bankaccservice.dto.BankAccountRequestDTO;
import com.tp.bankaccservice.dto.BankAccountResponseDTO;
import com.tp.bankaccservice.entities.BankAccount;
import com.tp.bankaccservice.mappers.AccountMapper;
import com.tp.bankaccservice.repositories.BankAccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

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
}
