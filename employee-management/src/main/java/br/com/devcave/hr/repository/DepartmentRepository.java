package br.com.devcave.hr.repository;

import br.com.devcave.hr.domain.entity.Department;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentRepository extends CrudRepository<Department, Long> {
    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Long id);

    boolean existsByIdAndEmployeesNotEmpty(Long id);
}
