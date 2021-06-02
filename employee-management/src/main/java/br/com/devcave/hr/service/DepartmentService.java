package br.com.devcave.hr.service;

import br.com.devcave.hr.department.DepartmentRepository;
import br.com.devcave.hr.domain.entity.Department;
import br.com.devcave.hr.domain.request.DepartmentRequest;
import br.com.devcave.hr.mapper.DepartmentMapper;
import br.com.devcave.hr.response.DepartmentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    private final DepartmentMapper departmentMapper;

    @Transactional
    public DepartmentResponse create(final DepartmentRequest request) {
        if (departmentRepository.existsByName(request.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Department already exists");
        }
        final Department department = departmentMapper.departmentRequestToEntity(request);

        return departmentMapper.departmentEntityToResponse(departmentRepository.save(department));
    }
}
