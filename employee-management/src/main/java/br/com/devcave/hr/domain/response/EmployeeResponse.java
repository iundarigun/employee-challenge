package br.com.devcave.hr.domain.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeResponse {
    private final Long id;

    private final String name;

    private final Long age;

    private final Integer height;

    private final Double weight;

    private final String description;

    private final DepartmentResponse department;
}
