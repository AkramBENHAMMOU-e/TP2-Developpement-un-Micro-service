package com.tp.bankaccservice.mappers;

import com.tp.bankaccservice.dto.BankAccountRequestDTO;
import com.tp.bankaccservice.dto.BankAccountResponseDTO;
import com.tp.bankaccservice.entities.BankAccount;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class AccountMapper {
    public BankAccountResponseDTO fromBankAccount(BankAccount bankAccount){
        BankAccountResponseDTO bankAccountResponseDTO = new BankAccountResponseDTO();
        BeanUtils.copyProperties(bankAccount,bankAccountResponseDTO);
        return bankAccountResponseDTO;
    }

    public BankAccount fromBankAccountRequestDTO(BankAccountRequestDTO dto) {
        BankAccount bankAccount = new BankAccount();
        BeanUtils.copyProperties(dto, bankAccount);
        bankAccount.setId(UUID.randomUUID().toString());
        bankAccount.setCreatedAt(new Date());
        return bankAccount;
    }

}
