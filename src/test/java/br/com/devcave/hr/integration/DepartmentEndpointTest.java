package br.com.devcave.hr.integration;

import br.com.devcave.hr.controller.handler.ErrorResponse;
import br.com.devcave.hr.domain.entity.Department;
import br.com.devcave.hr.domain.response.DepartmentResponse;
import br.com.devcave.hr.factory.DepartmentFactory;
import br.com.devcave.hr.factory.EmployeeFactory;
import br.com.devcave.hr.repository.DepartmentRepository;
import br.com.devcave.hr.repository.EmployeeRepository;
import io.restassured.RestAssured;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static br.com.devcave.hr.configuration.FakerConfiguration.FAKER;

@RequiredArgsConstructor
public class DepartmentEndpointTest extends AbstractEndpointTest {

    private final DepartmentFactory departmentFactory;

    private final EmployeeFactory employeeFactory;

    private final DepartmentRepository departmentRepository;

    private final EmployeeRepository employeeRepository;

    @BeforeEach
    void beforeEach() {
        employeeRepository.deleteAll();
        departmentRepository.deleteAll();
    }

    @Test
    @DisplayName("Get all departments successfully")
    void getAllSuccessfully() {
        final List<Department> list = departmentFactory.create();

        final DepartmentResponse[] response = RestAssured.given()
                .when()
                .get("/departments")
                .then()
                .statusCode(HttpStatus.OK.value())
                .and()
                .extract()
                .body()
                .as(DepartmentResponse[].class);

        Assertions.assertEquals(list.size(), response.length);
        list.stream().forEach(
                expected -> {
                    Optional<DepartmentResponse> departmentResponse = Arrays.stream(response).filter(it ->
                            it.getId().equals(expected.getId())
                                    && it.getName().equals(expected.getName())).findFirst();
                    Assertions.assertTrue(departmentResponse.isPresent());
                }
        );
    }

    @Test
    @DisplayName("find departament by id successfully")
    void findByIdSuccessfully() {
        final Department expected = departmentFactory.create(1).get(0);

        final DepartmentResponse response = RestAssured.given()
                .when()
                .pathParam("id", expected.getId())
                .get("/departments/{id}")
                .then()
                .statusCode(HttpStatus.OK.value())
                .and()
                .extract()
                .body()
                .as(DepartmentResponse.class);

        Assertions.assertEquals(expected.getId(), response.getId());
        Assertions.assertEquals(expected.getName(), response.getName());
    }

    @Test
    @DisplayName("find departament by id not found")
    void findByIdNotFound() {
        final Long id = FAKER.number().numberBetween(10_000L, 100_000L);

        final ErrorResponse response = RestAssured.given()
                .when()
                .pathParam("id", id)
                .get("/departments/{id}")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .and()
                .extract()
                .body()
                .as(ErrorResponse.class);

        Assertions.assertEquals(1, response.getErrors().size());
        Assertions.assertEquals("Department not found", response.getErrors().get(0));
    }

    @Test
    @DisplayName("delete departament by id successfully")
    void deleteByIdSuccessfully() {
        final Department expected = departmentFactory.create(1).get(0);
        final Long total = departmentRepository.count();

        RestAssured.given()
                .when()
                .pathParam("id", expected.getId())
                .delete("/departments/{id}")
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());

        Assertions.assertEquals(total - 1, departmentRepository.count());
    }

    @Test
    @DisplayName("delete departament by id not found")
    void deleteByIdNotFound() {
        final Long id = FAKER.number().numberBetween(10_000L, 100_000L);
        final ErrorResponse response = RestAssured.given()
                .when()
                .pathParam("id", id)
                .delete("/departments/{id}")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .and()
                .extract()
                .body()
                .as(ErrorResponse.class);

        Assertions.assertEquals(1, response.getErrors().size());
        Assertions.assertEquals("Department not found", response.getErrors().get(0));
    }

    @Test
    @DisplayName("delete departament by id has employees")
    void deleteByIdWithEmployees() {
        final Department expected = departmentFactory.create(1).get(0);
        employeeFactory.create(expected);
        final Long total = departmentRepository.count();

        final ErrorResponse response = RestAssured.given()
                .when()
                .pathParam("id", expected.getId())
                .delete("/departments/{id}")
                .then()
                .statusCode(HttpStatus.PRECONDITION_FAILED.value())
                .and()
                .extract()
                .body()
                .as(ErrorResponse.class);

        Assertions.assertEquals(total, departmentRepository.count());
        Assertions.assertEquals(1, response.getErrors().size());
        Assertions.assertEquals("Department has employees", response.getErrors().get(0));
    }
}
