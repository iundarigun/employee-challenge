package br.com.devcave.hr.domain.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class DepartmentResponse implements Serializable {
    private final Long id;

    private final String name;
}
