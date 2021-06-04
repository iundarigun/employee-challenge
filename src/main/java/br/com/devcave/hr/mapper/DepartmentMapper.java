package br.com.devcave.hr.mapper;

import br.com.devcave.hr.domain.entity.Department;
import br.com.devcave.hr.domain.request.DepartmentRequest;
import br.com.devcave.hr.domain.response.DepartmentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "employees", ignore = true),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true)
    })
    Department departmentRequestToEntity(DepartmentRequest request);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "employees", ignore = true),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true)
    })
    void updateEntity(DepartmentRequest request, @MappingTarget Department entity);

    DepartmentResponse departmentEntityToResponse(Department entity);
}
