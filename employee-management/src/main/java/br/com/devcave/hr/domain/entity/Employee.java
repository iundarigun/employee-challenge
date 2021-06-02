package br.com.devcave.hr.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class Employee extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private LocalDate birthday;

    private Integer height;

    private Double weight;

    private String description;

    @ManyToOne(optional = false)
    private Department department;
}
