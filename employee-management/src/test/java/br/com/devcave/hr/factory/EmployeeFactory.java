package br.com.devcave.hr.factory;

import br.com.devcave.hr.domain.entity.Department;
import br.com.devcave.hr.domain.entity.Employee;
import br.com.devcave.hr.domain.entity.JobType;
import br.com.devcave.hr.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static br.com.devcave.hr.configuration.FakerConfiguration.FAKER;

@Component
@RequiredArgsConstructor
public class EmployeeFactory {
    private final EmployeeRepository employeeRepository;

    public List<Employee> create(final Department department) {
        return create(department, FAKER.number().numberBetween(2, 5));
    }

    public List<Employee> create(final Department department, final Integer quantity) {
        return IntStream.range(0, quantity).boxed()
                .map(it -> employeeRepository.save(build(department)))
                .collect(Collectors.toList());
    }

    private Employee build(final Department department) {
        return Employee.builder()
                .name(FAKER.name().fullName())
                .job(FAKER.options().option(JobType.class))
                .birthday(LocalDate.ofInstant(Instant.ofEpochMilli(
                        FAKER.date().between(
                        Date.from(LocalDateTime.now().minus(70, ChronoUnit.YEARS).toInstant(ZoneOffset.UTC)),
                        Date.from(LocalDateTime.now().minus(18, ChronoUnit.YEARS).toInstant(ZoneOffset.UTC))).getTime()),
                        ZoneId.systemDefault()))
                .height(FAKER.number().numberBetween(160, 200))
                .weight(FAKER.number().randomDouble(2, 70, 120))
                .description(FAKER.lorem().characters(10, 1000))
                .department(department)
                .build();
    }
}
