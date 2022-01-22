package com.loanapp.egedemirbas.User.Entity;

import com.loanapp.egedemirbas.Credit.Enum.EnumGuaranteeType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "MPM_USER", schema = "public")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    /*
    * ad-soyad
    * Kimlik numarası
    * aylık geli
    * telefon bilgileri
    * doğum tarihi ve teminat
    * */
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String surname;
    private String identityNumber;
    private String phoneNumber;
    private BigDecimal salary;
    private Date dateOfBirth;
    private EnumGuaranteeType enumGuaranteeType;
}
