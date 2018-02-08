package ru.zhukov.ait.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "x_journal_detail")
@Data
@EqualsAndHashCode(of = {"id"})
public class TransferJournalDetail  implements Serializable{
   @Id
   @Column(name = "key_f")
   private String id;
    @Column(name = "mm")
   private int mm;
    @Column(name = "yy")
   private int yy;
    @Column(name = "days")
   private BigDecimal countDay;
    @Column(name = "hours")
   private BigDecimal countHour;
}
