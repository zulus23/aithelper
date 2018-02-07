package ru.zhukov.ait.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "const")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = {"id"})
public class Employee {
    @Id
    @Column(name = "pers_id")
    private String id;
    @Column(name = "tabel_n")
    private String tabelNumber;

    @Column(name = "first_name")
    private String name;
    @Column(name = "name")
    private String surname;
    @Column(name = "sec_name")
    private String lastname;



    public String getFullName(){
        return String.format("%s %s %s",getSurname().trim(),getName().trim(),getLastname().trim());
    }

}

