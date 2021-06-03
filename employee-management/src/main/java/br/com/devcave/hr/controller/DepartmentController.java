package br.com.devcave.hr.controller;

import br.com.devcave.hr.domain.request.DepartmentRequest;
import br.com.devcave.hr.domain.response.DepartmentResponse;
import br.com.devcave.hr.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<DepartmentResponse> create(@Valid @RequestBody final DepartmentRequest request) {
        final DepartmentResponse response = departmentService.create(request);
        final URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .build(response.getId());
        return ResponseEntity.created(location).body(response);
    }

    @PutMapping("{id}")
    public ResponseEntity<DepartmentResponse> update(@PathVariable final Long id,
                                                     @Valid @RequestBody final DepartmentRequest request) {

        final DepartmentResponse response = departmentService.update(id, request);

        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<DepartmentResponse> findById(@PathVariable final Long id) {
        final DepartmentResponse response = departmentService.findById(id);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<DepartmentResponse>> findAll() {
        final List<DepartmentResponse> responseList = departmentService.findAll();

        return ResponseEntity.ok(responseList);
    }

}
