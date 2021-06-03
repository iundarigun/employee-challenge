package br.com.devcave.hr.domain.response;

import br.com.devcave.hr.domain.entity.JobType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeResponse implements Serializable {
    private final Long id;

    private final String name;

    private final Long age;

    private final JobType job;

    private final Integer height;

    private final Double weight;

    private final String description;

    private final DepartmentResponse department;
}
