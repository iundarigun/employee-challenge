package br.com.devcave.hr.service;

import br.com.devcave.hr.client.RegisterClient;
import br.com.devcave.hr.domain.response.EmployeeResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegisterService {
    private final RegisterClient registerClient;

    @Retry(name = "retry-register", fallbackMethod = "registerFallback")
    @CircuitBreaker(name = "circuitbreaker-register")
    public Boolean register(final EmployeeResponse employeeResponse) {
        log.info("register job={}, employeeResponsehash={}", employeeResponse.getJob(), employeeResponse.hashCode());
        registerClient.register(employeeResponse.getJob(), employeeResponse);
        return true;
    }

    private Boolean registerFallback(final Throwable throwable) {
        log.warn("registerFallback");
        return false;
    }
}
