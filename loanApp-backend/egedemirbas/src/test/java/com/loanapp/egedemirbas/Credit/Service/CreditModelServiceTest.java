package com.loanapp.egedemirbas.Credit.Service;

import com.loanapp.egedemirbas.Credit.Entity.Credit;
import com.loanapp.egedemirbas.Credit.Enum.EnumCreditResult;
import com.loanapp.egedemirbas.User.Entity.User;
import com.loanapp.egedemirbas.User.Enum.EnumGuaranteeType;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CreditModelServiceTest {

    @Before
    private Long CalculateUserCredit(User user){
        long ageIndex;
        long guaranteeIndex = 0L;
        long creditScore;
        float salaryIndex = user.getSalary();

        if(user.getAge() >= 18 && user.getAge() < 25)
            ageIndex = 22L;
        else if(user.getAge() >= 25 && user.getAge() < 35)
            ageIndex = 25L;
        else
            ageIndex = 35L;
        if(user.getEnumGuaranteeType() == EnumGuaranteeType.GUARANTEED)
            guaranteeIndex = 100L;
        creditScore = Math.round(1.354 * ageIndex + 0.085 * salaryIndex + 2.12 * guaranteeIndex);
        return creditScore;
    }

    @After
    public Credit CreateUserCredit(User user){

        float totalCash;
        float guaranteeIndex = 0;
        float userCashLimit = 0;
        float userSalary = user.getSalary();

        int creditLimitMulti = 4;

        EnumCreditResult enumCreditResult = null;

        Long userCredit = this.CalculateUserCredit(user);

        if(userCredit < 500L){
            enumCreditResult = EnumCreditResult.REJECTED;
        }
        else if(userCredit  >= 500 && userCredit <= 1000){
            if(userSalary < 5000){
                userCashLimit = 10000;
                guaranteeIndex = (float) 0.10;
            }
            else if(userSalary >= 5000 && userSalary <= 10000){
                userCashLimit = 20000;
                guaranteeIndex = (float) 0.20;
            }
            else if( userSalary > 10000){
                userCashLimit = userSalary * creditLimitMulti / 2;
                guaranteeIndex = (float) 0.25;
            }
            enumCreditResult = EnumCreditResult.CONFIRMED;
        }
        else if(userSalary > 10000){
            userCashLimit = userSalary * creditLimitMulti;
            guaranteeIndex = (float) 0.50;
            enumCreditResult = EnumCreditResult.CONFIRMED;
        }

        if(user.getEnumGuaranteeType() == EnumGuaranteeType.GUARANTEED)
            totalCash = userCashLimit + user.getGuaranteeAmount() * guaranteeIndex;
        else
            totalCash = userCashLimit;

        Credit credit = new Credit();
        credit.setInsDate(new Date());
        credit.setCreditResult(enumCreditResult);
        credit.setUserDateOfBirth(user.getDateOfBirth());
        credit.setUserIdentityNumber(user.getIdentityNumber());
        credit.setCreditAmount(totalCash);
        return credit;
    }

    @After
    public Credit CreateUserCredit(User user, Long userCredit){

        float totalCash;
        float guaranteeIndex = 0;
        float userCashLimit = 0;
        float userSalary = user.getSalary();

        int creditLimitMulti = 4;

        EnumCreditResult enumCreditResult = null;

        if(userCredit < 500L){
            enumCreditResult = EnumCreditResult.REJECTED;
        }
        else if(userCredit  >= 500 && userCredit <= 1000){
            if(userSalary < 5000){
                userCashLimit = 10000;
                guaranteeIndex = (float) 0.10;
            }
            else if(userSalary >= 5000 && userSalary <= 10000){
                userCashLimit = 20000;
                guaranteeIndex = (float) 0.20;
            }
            else if( userSalary > 10000){
                userCashLimit = userSalary * creditLimitMulti / 2;
                guaranteeIndex = (float) 0.25;
            }
            enumCreditResult = EnumCreditResult.CONFIRMED;
        }
        else if(userSalary > 10000){
            userCashLimit = userSalary * creditLimitMulti;
            guaranteeIndex = (float) 0.50;
            enumCreditResult = EnumCreditResult.CONFIRMED;
        }

        if(user.getEnumGuaranteeType() == EnumGuaranteeType.GUARANTEED)
            totalCash = userCashLimit + user.getGuaranteeAmount() * guaranteeIndex;
        else
            totalCash = userCashLimit;

        Credit credit = new Credit();
        credit.setInsDate(new Date());
        credit.setCreditResult(enumCreditResult);
        credit.setUserDateOfBirth(user.getDateOfBirth());
        credit.setUserIdentityNumber(user.getIdentityNumber());
        credit.setCreditAmount(totalCash);
        return credit;
    }

    //Kredi skoru 500’ün altında ise kullanıcı reddedilir. (Kredi sonucu: Red)
    @Test
    void shouldReturnRejected(){

        User user = new User();
        user.setDateOfBirth(new Date());
        user.setName("John");
        user.setSurname("Doe");
        user.setAge(50L);
        user.setSalary(100);
        user.setEnumGuaranteeType(EnumGuaranteeType.NONE);

        Credit credit = CreateUserCredit(user);
        EnumCreditResult result = credit.getCreditResult();

        System.out.print("shouldReturnRejected completed with result: " + result.toString() + "\n");
        System.out.print("credit amount: " + credit.getCreditAmount() + "\n");

        assertEquals(result, EnumCreditResult.REJECTED);
    }

    @Test
    void shouldReturnConfirmed(){

        User user = new User();
        user.setDateOfBirth(new Date());
        user.setName("Murat");
        user.setSurname("Güzel");
        user.setAge(30L);
        user.setSalary(20000);
        user.setEnumGuaranteeType(EnumGuaranteeType.GUARANTEED);

        Credit credit = CreateUserCredit(user);
        EnumCreditResult result = credit.getCreditResult();

        System.out.print("shouldReturnRejected completed with result: " + result.toString() + "\n");
        System.out.print("credit amount: " + credit.getCreditAmount() + "\n");

        assertEquals(result, EnumCreditResult.CONFIRMED);
    }

    /*
    *   Kredi skoru 500 puan ile 1000 puan arasında ise ve aylık geliri 5000 TL’nin altında ise
*●      kullanıcının kredi başvurusu onaylanır ve kullanıcıya 10.000 TL limit atanır. (Kredi Sonucu:
    *   Onay). Eğer teminat vermişse teminat bedelinin yüzde 10 u kadar tutar kredi limitine
    *   eklenir.
    */
    @Test
    void shouldGetTenThousandCreditAmount(){
        User user = new User();
        user.setDateOfBirth(new Date());
        user.setName("John");
        user.setSurname("Doe");
        user.setAge(25L);
        user.setSalary(4999);
        user.setGuaranteeAmount(1000L);
        user.setEnumGuaranteeType(EnumGuaranteeType.GUARANTEED);

        Credit credit = CreateUserCredit(user, 550L);

        System.out.print("User credit result is: " + credit.getCreditAmount() + "\n");
        System.out.print("credit amount: " + credit.getCreditAmount() + "\n");
        assertEquals(credit.getCreditAmount(), 10100L);
    }
}