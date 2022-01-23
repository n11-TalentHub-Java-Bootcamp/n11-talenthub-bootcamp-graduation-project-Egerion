package com.loanapp.egedemirbas.Credit.CreditModel;

import com.loanapp.egedemirbas.Credit.Service.CreditEntityService;
import com.loanapp.egedemirbas.Generic.Log.TestLog;
import com.loanapp.egedemirbas.User.Entity.User;
import com.loanapp.egedemirbas.User.Enum.EnumGuaranteeType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreditModel {

    private final CreditEntityService creditEntityService;

    private Long CalculateUserCredit(User user){
        Long ageIndex = 0L;
        Long guaranteeIndex = 0L;
        Long creditScore = 0L;
        Long salaryIndex = user.getSalary().longValue();

        if(user.getAge() >= 18 && user.getAge() < 25)
            ageIndex = 22L;
        else if(user.getAge() >= 25 && user.getAge() < 35)
            ageIndex = 25L;
        else
            ageIndex = 35L;
        if(user.getEnumGuaranteeType() == EnumGuaranteeType.GUARANTEED)
            guaranteeIndex = 100L;
        creditScore = Math.round(1.354 * ageIndex + 0.085 * salaryIndex + 2.12 * guaranteeIndex);

        TestLog.print("Class: CalculateUserCredit called with final credit score value of: " + creditScore);
        return creditScore;
    }
}
