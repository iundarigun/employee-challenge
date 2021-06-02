package br.com.devcave.hr.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String name;

    private LocalDate birthday;

    private Integer height;

    private Double weight;

    private String description;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
