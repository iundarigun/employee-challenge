package br.com.devcave.hr.repository;

import br.com.devcave.hr.domain.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    boolean existsByName(String name);

    Page<Employee> findBy(Pageable pageable);
}
