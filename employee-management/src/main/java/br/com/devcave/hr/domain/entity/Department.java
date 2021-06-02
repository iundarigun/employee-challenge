package br.com.devcave.hr.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class Department extends BaseEntity {
    @Id
    private Long id;

    private String name;
}
