package br.com.devcave.hr.client;

import br.com.devcave.hr.domain.entity.JobType;
import br.com.devcave.hr.domain.response.EmployeeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "registerAPI", url = "${feign.client.config.registerAPI.url}")
public interface RegisterClient {
    @PostMapping("register/{job}")
    void register(@PathVariable JobType job, @RequestBody EmployeeResponse employeeResponse);
}
