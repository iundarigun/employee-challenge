package br.com.devcave.hr.domain.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
@AllArgsConstructor(onConstructor = @__({@JsonCreator}))
public class DepartmentRequest {
    @NotEmpty
    private String name;
}
