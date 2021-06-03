package br.com.devcave.hr.factory;

import br.com.devcave.hr.domain.entity.Department;
import br.com.devcave.hr.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static br.com.devcave.hr.configuration.FakerConfiguration.FAKER;

@Component
@RequiredArgsConstructor
public class DepartmentFactory {
    private final DepartmentRepository departmentRepository;

    public List<Department> create() {
        return create(FAKER.number().numberBetween(2, 5));
    }

    public List<Department> create(final Integer quantity) {
        return IntStream.range(0, quantity).boxed()
                .map(it -> departmentRepository.save(build()))
                .collect(Collectors.toList());
    }

    private Department build() {
        return Department.builder().name(FAKER.commerce().department()).build();
    }
}
