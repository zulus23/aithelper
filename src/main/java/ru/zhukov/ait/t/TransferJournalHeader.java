package ru.zhukov.ait.t;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.zhukov.ait.domain.Employee;
import ru.zhukov.ait.domain.Order;
import ru.zhukov.ait.domain.TypeVacation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "x_journal")
@Data
@EqualsAndHashCode(of = {"id"})
public class TransferJournalHeader  implements Serializable{
    @Id
    @Column(name = "key_f")
    private String id;
    private Employee employee;
    private TypeVacation typeVacation;
    private String docNumber;
    private String docDate;
    private String userId;
    private LocalDate dateBegin;
    private LocalDate dateEnd;
    @Column(name = "sred_days_hours")
    private BigDecimal averageDayHour;
    @Column(name = "sred_fot")
    private BigDecimal averageFoundSalaryPay;
    @Column(name = "sred_fmp")
    private BigDecimal averageFMP;
    @Column(name = "sred_region")
    private BigDecimal averageRegion;
    @Column(name = "fmp_last_year_1_12")
    private BigDecimal fmpLastYear;
    @Column(name = "fl_pay")
    private Boolean calculate;
    @Column(name = "key_kdr")
    private Order order;


    @Column(name = "days_otp_av")
    private BigDecimal countDay;

    @Column(name = "date_create")
    private LocalDate dateCreate;


}
