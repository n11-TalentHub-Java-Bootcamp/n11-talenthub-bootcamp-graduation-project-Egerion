package com.loanapp.egedemirbas.User.Dto;

import com.loanapp.egedemirbas.Credit.Enum.EnumGuaranteeType;

import java.math.BigDecimal;
import java.util.Date;

public class UserDto {

    private Long id;
    private String name;
    private String surname;
    private String identityNumber;
    private String phoneNumber;
    private BigDecimal salary;
    private Date dateOfBirth;
    private EnumGuaranteeType enumGuaranteeType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public EnumGuaranteeType getEnumGuaranteeType() {
        return enumGuaranteeType;
    }

    public void setEnumGuaranteeType(EnumGuaranteeType enumGuaranteeType) {
        this.enumGuaranteeType = enumGuaranteeType;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", identityNumber='" + identityNumber + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", salary=" + salary +
                ", dateOfBirth=" + dateOfBirth +
                ", enumGuaranteeType=" + enumGuaranteeType +
                '}';
    }
}
