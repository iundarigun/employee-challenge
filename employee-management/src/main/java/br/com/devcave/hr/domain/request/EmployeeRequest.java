package br.com.devcave.hr.domain.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.LocalDate;

@Getter
@AllArgsConstructor(onConstructor = @__({@JsonCreator}))
public class EmployeeRequest {
    @Null(groups = EmployeeUpdate.class)
    @NotEmpty(groups = EmployeeCreate.class)
    private final String name;

    @NotNull
    private final LocalDate birthday;

    private final Integer height;

    private final Double weight;

    private final String description;

    @NotNull
    private final Long departmentId;

    @AssertTrue(message = "This employee is underage")
    private boolean isValidBirthday() {
        return (birthday == null || birthday.isBefore(LocalDate.now().minusYears(18)));
    }

    public interface EmployeeCreate {
    }

    public interface EmployeeUpdate {
    }

}
