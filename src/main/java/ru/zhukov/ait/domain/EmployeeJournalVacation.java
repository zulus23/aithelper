package ru.zhukov.ait.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.mapping.ToOne;

import javax.persistence.*;

@Entity
@Table(name = "y_jur_otp")
@Data
@EqualsAndHashCode(of = {"id"})
public class EmployeeJournalVacation {
    @Id
    @Column(name = "key_f")
    private String id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "keyf_pr")
    private  Order order;

}
