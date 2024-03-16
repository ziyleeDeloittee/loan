package com.ziylee.loan.service;

import com.ziylee.loan.domain.dto.LoanDto;

public interface ILoanService {

    void createLoan(String mobileNumber);

    LoanDto fetchLoan(String mobileNumber);

    boolean updateLoan(LoanDto loanDto);

    boolean deleteLoan(String mobileNumber);
}
