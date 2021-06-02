package br.com.devcave.hr.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DepartmentResponse {
    private final Long id;

    private final String name;
}
