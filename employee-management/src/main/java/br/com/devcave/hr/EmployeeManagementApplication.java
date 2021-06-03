package br.com.devcave.hr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class EmployeeManagementApplication {

    public static void main(final String[] args) {
        SpringApplication.run(EmployeeManagementApplication.class, args);
    }

}
