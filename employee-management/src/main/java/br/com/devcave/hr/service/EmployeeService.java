package br.com.devcave.hr.service;

import br.com.devcave.hr.domain.entity.Employee;
import br.com.devcave.hr.domain.request.EmployeeRequest;
import br.com.devcave.hr.domain.response.EmployeeResponse;
import br.com.devcave.hr.domain.response.PageResponse;
import br.com.devcave.hr.exception.ApplicationException;
import br.com.devcave.hr.mapper.EmployeeMapper;
import br.com.devcave.hr.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final DepartmentService departmentService;

    private final EmployeeRepository employeeRepository;

    private final EmployeeMapper employeeMapper;

    @Transactional
    @Caching(evict = {
            @CacheEvict(value = "employeeList", allEntries = true),
            @CacheEvict(value = "employee", key = "'findById:'.concat(#id)")
    })
    public EmployeeResponse create(final EmployeeRequest request) {
        if (employeeRepository.existsByName(request.getName())) {
            throw new ApplicationException(HttpStatus.BAD_REQUEST, "Employee already exists");
        }
        final Employee entity = employeeMapper.employeeRequestToEntity(request,
                departmentService.findByIdAsEntity(request.getDepartmentId()));

        return employeeMapper.employeeEntityToDetailResponse(employeeRepository.save(entity));
    }

    @Transactional
    @Caching(evict = {
            @CacheEvict(value = "employeeList", allEntries = true),
            @CacheEvict(value = "employee", key = "'findById:'.concat(#id)")
    })
    public EmployeeResponse update(final Long id, final EmployeeRequest request) {
        final Employee entity = employeeRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(HttpStatus.NOT_FOUND, "Employee not found"));

        employeeMapper.updateEntity(request, departmentService.findByIdAsEntity(request.getDepartmentId()), entity);

        return employeeMapper.employeeEntityToDetailResponse(employeeRepository.save(entity));
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "employee", key = "'findById:'.concat(#id)")
    public EmployeeResponse findById(final Long id) {
        final Employee entity = employeeRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(HttpStatus.NOT_FOUND, "Employee not found"));

        return employeeMapper.employeeEntityToDetailResponse(entity);
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "employeeList")
    public PageResponse<EmployeeResponse> findByParams(final Integer page, final Integer size) {
        final Page<Employee> result = employeeRepository.findBy(PageRequest.of(page - 1, size));

        final List<EmployeeResponse> content = result.getContent()
                .stream()
                .map(employeeMapper::employeeEntityToResponse)
                .collect(Collectors.toList());

        return new PageResponse<>(page, result.getTotalPages(), result.getTotalElements(), content);
    }

    @Transactional
    @Caching(evict = {
            @CacheEvict(value = "employeeList", allEntries = true),
            @CacheEvict(value = "employee", key = "'findById:'.concat(#id)")
    })
    public void delete(final Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new ApplicationException(HttpStatus.NOT_FOUND, "Employee not found");
        }
        employeeRepository.deleteById(id);
    }
}
