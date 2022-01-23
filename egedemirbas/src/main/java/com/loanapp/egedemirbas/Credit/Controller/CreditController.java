package com.loanapp.egedemirbas.Credit.Controller;

import com.loanapp.egedemirbas.Credit.Entity.Credit;
import com.loanapp.egedemirbas.Credit.Exception.CreditNotFoundException;
import com.loanapp.egedemirbas.Credit.Service.CreditEntityService;
import com.loanapp.egedemirbas.User.Service.UserEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/credit")
@CrossOrigin
@RequiredArgsConstructor
public class CreditController {

    private final CreditEntityService creditEntityService;
    private final UserEntityService userEntityService;

    @GetMapping("/list")
    public List<Credit> findALlCredits(){
        return creditEntityService.findAllCredits();
    }

    @GetMapping("/{userIdentityNumber}/{userDateOfBirth}")
    public Credit findAllByUserIdentityNumberAndUserDateOfBirth(@PathVariable Long userIdentityNumber, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date userDateOfBirth){
        Credit credit = creditEntityService.findAllByUserIdentityNumberAndUserDateOfBirth(userIdentityNumber, userDateOfBirth);
        if(credit == null)
            throw new CreditNotFoundException("Credit not found belonging to " + userEntityService.findUserByIdentityNumber(userIdentityNumber).getName());
        return credit;
    }
}