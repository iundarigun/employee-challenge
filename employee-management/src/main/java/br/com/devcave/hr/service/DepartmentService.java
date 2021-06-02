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

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
        final Department entity = departmentMapper.departmentRequestToEntity(request);

        return departmentMapper.departmentEntityToResponse(departmentRepository.save(entity));
    }

    @Transactional
    public DepartmentResponse update(final Long id, final DepartmentRequest request) {
        final Department entity = departmentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Department not found"));

        if (departmentRepository.existsByNameAndIdNot(request.getName(), id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Department already exists");
        }

        departmentMapper.updateEntity(request, entity);

        return departmentMapper.departmentEntityToResponse(departmentRepository.save(entity));
    }

    @Transactional(readOnly = true)
    public DepartmentResponse findById(final Long id) {
        final Department entity = departmentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Department not found"));

        return departmentMapper.departmentEntityToResponse(entity);
    }

    @Transactional(readOnly = true)
    public List<DepartmentResponse> findAll() {
        final Iterable<Department> list = departmentRepository.findAll();

        return StreamSupport
                .stream(list.spliterator(), false)
                .map(departmentMapper::departmentEntityToResponse)
                .collect(Collectors.toList());
    }

}
