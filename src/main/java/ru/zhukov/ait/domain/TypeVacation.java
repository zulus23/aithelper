package ru.zhukov.ait.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sp_otp_tips")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = {"id"})
public class TypeVacation {
    @Id
    @Column(name = "kod")
    private String id;
    @Column(name = "name_")
    private String description;

}
