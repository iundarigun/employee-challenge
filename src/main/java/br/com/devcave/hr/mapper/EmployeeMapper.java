package br.com.devcave.hr.mapper;

import br.com.devcave.hr.domain.entity.Department;
import br.com.devcave.hr.domain.entity.Employee;
import br.com.devcave.hr.domain.request.EmployeeRequest;
import br.com.devcave.hr.domain.response.EmployeeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = DepartmentMapper.class)
public interface EmployeeMapper {
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "name", source = "request.name"),
            @Mapping(target = "department", source = "department"),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true)
    })
    Employee employeeRequestToEntity(EmployeeRequest request, Department department);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "name", ignore = true),
            @Mapping(target = "department", source = "department"),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true)
    })
    void updateEntity(EmployeeRequest request, Department department, @MappingTarget Employee entity);

    @Mappings({
            @Mapping(target = "height", ignore = true),
            @Mapping(target = "weight", ignore = true),
            @Mapping(target = "age", expression = "java(entity.getBirthday().until(java.time.LocalDate.now(), java.time.temporal.ChronoUnit.YEARS))"),
            @Mapping(target = "description", ignore = true),
            @Mapping(target = "department", ignore = true)
    })
    EmployeeResponse employeeEntityToResponse(Employee entity);

    @Mappings({
            @Mapping(target = "age", expression = "java(entity.getBirthday().until(java.time.LocalDate.now(), java.time.temporal.ChronoUnit.YEARS))")
    })
    EmployeeResponse employeeEntityToDetailResponse(Employee entity);
}
