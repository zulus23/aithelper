package ru.zhukov.ait.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "prikaz")

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = {"id"})

public class Order {
    @Id
    @Column(name = "key_f")
    private String id;
    @Column(name = "data_pr")
    private LocalDate  date;
    @Column(name = "nomer")
    private String number;
    @OneToOne
    @JoinColumn(name = "pers_id")
    private Employee employee;
    @Column(name = "date_begin")
    private LocalDate  dateBegin;
    @Column(name = "date_end")
    private LocalDate  dateEnd;
    @Column(name = "days")
    private BigDecimal day;
    @OneToOne
    @JoinColumn(name = "tip")
    private TypeOrder typeOrder;

    @Column(name = "flag_z")
    private Boolean statusOrder;

    @OneToOne
    @JoinColumn(name = "otp_tip")
    private TypeVacation typeVacation;



}
