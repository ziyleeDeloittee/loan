package com.ziylee.loan.service.impl;

import com.ziylee.loan.constant.LoanConstant;
import com.ziylee.loan.domain.dto.LoanDto;
import com.ziylee.loan.domain.entity.Loan;
import com.ziylee.loan.exception.LoanAlreadyExistException;
import com.ziylee.loan.exception.ResourceNotFoundException;
import com.ziylee.loan.mapper.LoanMapper;
import com.ziylee.loan.repository.LoanRepository;
import com.ziylee.loan.service.ILoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements ILoanService {
    private final LoanRepository loanRepository;

    @Override
    public void createLoan(String mobileNumber) {
        Optional<Loan> optionalLoan = loanRepository.findByMobileNumber(mobileNumber);
        if (optionalLoan.isPresent()) {
            throw new LoanAlreadyExistException("Loan already registered with given mobile number: " + mobileNumber);
        }
        loanRepository.save(createNewLoan(mobileNumber));
    }

    @Override
    public LoanDto fetchLoan(String mobileNumber) {
        Loan loan = loanRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber));

        return LoanMapper.mapToLoanDto(loan);
    }

    @Override
    public boolean updateLoan(LoanDto loanDto) {
        Loan existingLoan = loanRepository.findByLoanNumber(loanDto.loanNumber())
                .orElseThrow(() -> new ResourceNotFoundException("Loan", "loanNumber", loanDto.loanNumber()));

        LoanMapper.mapToLoan(loanDto, existingLoan);
        loanRepository.save(existingLoan);
        return true;
    }

    @Override
    public boolean deleteLoan(String mobileNumber) {
        Loan loan = loanRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber));

        loanRepository.deleteById(loan.getLoanId());
        return true;
    }

    /**
     * @param mobileNumber client's mobile number
     * @return new loan based on client mobile number
     */
    private Loan createNewLoan(String mobileNumber) {
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        return Loan.builder()
                .mobileNumber(mobileNumber)
                .loanNumber(String.valueOf(randomLoanNumber))
                .loanType(LoanConstant.HOME_LOAN)
                .totalLoan(LoanConstant.NEW_LOAN_LIMIT)
                .amountPaid(0)
                .outstandingAmount(LoanConstant.NEW_LOAN_LIMIT)
                .build();
    }
}
