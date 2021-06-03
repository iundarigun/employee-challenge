package br.com.devcave.hr.service;

import br.com.devcave.hr.domain.response.DepartmentResponse;
import br.com.devcave.hr.repository.DepartmentRepository;
import br.com.devcave.hr.domain.entity.Department;
import br.com.devcave.hr.domain.request.DepartmentRequest;
import br.com.devcave.hr.exception.ApplicationException;
import br.com.devcave.hr.mapper.DepartmentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
            throw new ApplicationException(HttpStatus.BAD_REQUEST, "Department already exists");
        }
        final Department entity = departmentMapper.departmentRequestToEntity(request);

        return departmentMapper.departmentEntityToResponse(departmentRepository.save(entity));
    }

    @Transactional
    public DepartmentResponse update(final Long id, final DepartmentRequest request) {
        final Department entity = departmentRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(HttpStatus.NOT_FOUND, "Department not found"));

        if (departmentRepository.existsByNameAndIdNot(request.getName(), id)) {
            throw new ApplicationException(HttpStatus.BAD_REQUEST, "Department already exists");
        }

        departmentMapper.updateEntity(request, entity);

        return departmentMapper.departmentEntityToResponse(departmentRepository.save(entity));
    }

    @Transactional(readOnly = true)
    public DepartmentResponse findById(final Long id) {
        final Department entity = departmentRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(HttpStatus.NOT_FOUND, "Department not found"));

        return departmentMapper.departmentEntityToResponse(entity);
    }

    @Transactional(readOnly = true)
    public Department findByIdAsEntity(final Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(HttpStatus.NOT_FOUND, "Department not found"));
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
