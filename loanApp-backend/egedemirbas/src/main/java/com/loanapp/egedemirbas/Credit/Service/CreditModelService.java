package com.loanapp.egedemirbas.Credit.Service;

import com.loanapp.egedemirbas.Credit.Entity.Credit;
import com.loanapp.egedemirbas.Credit.Enum.EnumCreditResult;
import com.loanapp.egedemirbas.Generic.Log.TestLog;
import com.loanapp.egedemirbas.User.Entity.User;
import com.loanapp.egedemirbas.User.Enum.EnumGuaranteeType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class CreditModelService {

    private final CreditEntityService creditEntityService;

    private Long CalculateUserCredit(User user){
        long ageIndex;
        long guaranteeIndex = 0L;
        long creditScore;
        double salaryIndex = user.getSalary();

        if(user.getAge() >= 18 && user.getAge() < 25)
            ageIndex = 22L;
        else if(user.getAge() >= 25 && user.getAge() < 35)
            ageIndex = 25L;
        else
            ageIndex = 35L;
        if(user.getEnumGuaranteeType() == EnumGuaranteeType.GUARANTEED)
            guaranteeIndex = 100L;
        creditScore = Math.round(1.354 * ageIndex + 0.085 * salaryIndex + 2.12 * guaranteeIndex);

        TestLog.print("Method: CalculateUserCredit called with final credit score value of: " + creditScore);
        return creditScore;
    }

    public EnumCreditResult CreateUserCredit(User user){

        Credit credit = null;
        double totalCash;
        double guaranteeIndex = 0;
        double userCashLimit = 0;
        double userSalary = user.getSalary();

        int creditLimitMulti = 4;

        EnumCreditResult enumCreditResult = null;

        Long userCredit = this.CalculateUserCredit(user);

        if(userCredit < 500L){
            enumCreditResult = EnumCreditResult.REJECTED;
        }
        else if(userCredit  > 500 && userCredit < 1000){
            if(userSalary < 5000){
                userCashLimit = 10000;
                guaranteeIndex = 0.10;
            }
            else if(userSalary > 5000 && userSalary < 10000){
                userCashLimit = 20.000;
                guaranteeIndex = 0.20;
            }
            else if( userSalary > 10000){
                userCashLimit = userSalary * creditLimitMulti / 2;
                guaranteeIndex = 0.25;
            }
            enumCreditResult = EnumCreditResult.CONFIRMED;
        }
        else if(userSalary >= 10000){
            userCashLimit = userSalary * creditLimitMulti;
            guaranteeIndex = 0.50;
            enumCreditResult = EnumCreditResult.CONFIRMED;
        }

        if(user.getEnumGuaranteeType() == EnumGuaranteeType.GUARANTEED)
            totalCash = userCashLimit + user.getGuaranteeAmount() * guaranteeIndex;
        else
            totalCash = userCashLimit;

        credit.setInsDate(new Date());
        credit.setCreditResult(enumCreditResult);
        credit.setUserDateOfBirth(user.getDateOfBirth());
        credit.setUserIdentityNumber(user.getIdentityNumber());
        credit.setCreditAmount(totalCash);
        creditEntityService.saveCredit(credit);

        TestLog.print("Method: CalculateUserCreditResult, Credit " + credit + " saved!");
        return enumCreditResult;
    }
}