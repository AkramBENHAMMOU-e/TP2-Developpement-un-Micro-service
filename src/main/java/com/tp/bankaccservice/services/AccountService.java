package com.tp.bankaccservice.services;

import com.tp.bankaccservice.dto.*;
import com.tp.bankaccservice.entities.BankAccount;

public interface AccountService {

    BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO);
    BankAccountResponseDTO updateAccount(String id, BankAccountRequestDTO bankAccountDTO);
    void deleteAccount(String id);
}
