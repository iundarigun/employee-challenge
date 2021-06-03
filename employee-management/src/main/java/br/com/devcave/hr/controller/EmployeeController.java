package br.com.devcave.hr.controller;

import br.com.devcave.hr.domain.request.EmployeeRequest;
import br.com.devcave.hr.domain.response.EmployeeResponse;
import br.com.devcave.hr.domain.response.PageResponse;
import br.com.devcave.hr.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.net.URI;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping
    @Validated(EmployeeRequest.EmployeeCreate.class)
    public ResponseEntity<EmployeeResponse> create(@Valid @RequestBody final EmployeeRequest request) {
        final EmployeeResponse response = employeeService.create(request);
        final URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .build(response.getId());
        return ResponseEntity.created(location).body(response);
    }

    @PutMapping("{id}")
    @Validated(EmployeeRequest.EmployeeUpdate.class)
    public ResponseEntity<EmployeeResponse> update(@PathVariable final Long id,
                                                   @Valid @RequestBody final EmployeeRequest request) {

        final EmployeeResponse response = employeeService.update(id, request);

        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<EmployeeResponse> findById(@PathVariable final Long id) {
        final EmployeeResponse response = employeeService.findById(id);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<PageResponse<EmployeeResponse>> findByParams(
            @RequestParam(required = false, defaultValue = "1")
            @Min(value = 1) final Integer page,
            @RequestParam(required = false, defaultValue = "20")
            @Min(value = 5)
            @Max(value = 50) final Integer size
    ) {
        final PageResponse<EmployeeResponse> responseList = employeeService.findByParams(page, size);

        return ResponseEntity.ok(responseList);
    }

}
