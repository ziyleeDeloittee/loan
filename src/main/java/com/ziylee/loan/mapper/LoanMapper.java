package com.ziylee.loan.mapper;

import com.ziylee.loan.domain.dto.LoanDto;
import com.ziylee.loan.domain.entity.Loan;

public class LoanMapper {

    public static LoanDto mapToLoanDto(Loan loan) {
        return LoanDto.builder()
                .mobileNumber(loan.getMobileNumber())
                .loanNumber(loan.getLoanNumber())
                .loanType(loan.getLoanType())
                .totalLoan(loan.getTotalLoan())
                .amountPaid(loan.getAmountPaid())
                .outstandingAmount(loan.getOutstandingAmount())
                .build();
    }

    public static Loan mapToLoan(LoanDto loanDto) {
        return Loan.builder()
                .mobileNumber(loanDto.mobileNumber())
                .loanNumber(loanDto.loanNumber())
                .loanType(loanDto.loanType())
                .totalLoan(loanDto.totalLoan())
                .amountPaid(loanDto.amountPaid())
                .outstandingAmount(loanDto.outstandingAmount())
                .build();
    }

    public static Loan mapToLoan(LoanDto loanDto, Loan existingLoan) {
        existingLoan.setMobileNumber(loanDto.mobileNumber());
        existingLoan.setLoanNumber(loanDto.loanNumber());
        existingLoan.setLoanType(loanDto.loanType());
        existingLoan.setLoanType(loanDto.loanType());
        existingLoan.setTotalLoan(loanDto.totalLoan());
        existingLoan.setAmountPaid(loanDto.amountPaid());
        existingLoan.setOutstandingAmount(loanDto.outstandingAmount());
        return existingLoan;
    }
}
