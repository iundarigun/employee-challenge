package br.com.devcave.hr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableCaching
@EnableFeignClients
@SpringBootApplication
public class EmployeeManagementApplication {

    public static void main(final String[] args) {
        SpringApplication.run(EmployeeManagementApplication.class, args);
    }

}
