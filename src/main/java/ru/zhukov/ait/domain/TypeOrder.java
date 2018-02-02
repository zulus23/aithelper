package ru.zhukov.ait.domain;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sp_pricaz")

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = {"id"})
public class TypeOrder {
  @Id
  @Column(name = "kode")
  private String id;
  @Column(name = "name_pr")
  private String name;
  @Column(name = "pref_pr")
  private String prefix;




}
