package com.tp.bankaccservice.services;

import com.tp.bankaccservice.dto.*;
import com.tp.bankaccservice.entities.BankAccount;

public interface AccountService {

    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO);
}
