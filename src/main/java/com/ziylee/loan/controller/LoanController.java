package com.ziylee.loan.controller;

import com.ziylee.loan.constant.LoanConstant;
import com.ziylee.loan.domain.dto.LoanDto;
import com.ziylee.loan.domain.dto.ResponseDto;
import com.ziylee.loan.service.ILoanService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/loans")
public class LoanController {
    private final ILoanService iLoanService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDto createLoan(@Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
                                  @RequestParam String mobileNumber) {
        iLoanService.createLoan(mobileNumber);
        return ResponseDto.builder()
                .statusCode(LoanConstant.STATUS_201)
                .statusMsg(LoanConstant.MESSAGE_201)
                .build();
    }

    @GetMapping("/fetch")
    public LoanDto fetchLoanDetails(@Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
                                    @RequestParam String mobileNumber) {
        return iLoanService.fetchLoan(mobileNumber);
    }

    @PutMapping("/update")
    public ResponseDto updateLoan(@Valid @RequestBody LoanDto loanDto) {
        boolean isUpdated = iLoanService.updateLoan(loanDto);
        if (isUpdated) {
            return ResponseDto.builder()
                    .statusCode(LoanConstant.STATUS_200)
                    .statusMsg(LoanConstant.MESSAGE_200)
                    .build();

        } else {
            return ResponseDto.builder()
                    .statusCode(LoanConstant.STATUS_417)
                    .statusMsg(LoanConstant.MESSAGE_417_UPDATE)
                    .build();
        }
    }

    @DeleteMapping("/delete")
    public ResponseDto deleteLoan(@Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
                                  @RequestParam String mobileNumber) {
        boolean isDeleted = iLoanService.deleteLoan(mobileNumber);
        if (isDeleted) {
            return ResponseDto.builder()
                    .statusCode(LoanConstant.STATUS_200)
                    .statusMsg(LoanConstant.MESSAGE_200)
                    .build();

        } else {
            return ResponseDto.builder()
                    .statusCode(LoanConstant.STATUS_417)
                    .statusMsg(LoanConstant.MESSAGE_417_UPDATE)
                    .build();
        }
    }
}
