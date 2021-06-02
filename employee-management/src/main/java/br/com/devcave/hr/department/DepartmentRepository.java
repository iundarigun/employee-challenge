package br.com.devcave.hr.department;

import br.com.devcave.hr.domain.entity.Department;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentRepository extends CrudRepository<Department, Long> {
    boolean existsByName(String name);
}
