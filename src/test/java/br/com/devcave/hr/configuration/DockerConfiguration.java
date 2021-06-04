package br.com.devcave.hr.configuration;

import org.springframework.context.annotation.Configuration;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;

@Configuration
public class DockerConfiguration {
    private static final GenericContainer<?> DATABASE = createDatabaseContainer();
    private static final GenericContainer<?> MOCK = createMockContainer();

    private static GenericContainer<?> createDatabaseContainer() {
        GenericContainer<?> container = new GenericContainer<>("mysql");
        container.withExposedPorts(3306);
        container.getPortBindings().add("13306:3306");
        container.addEnv("MYSQL_DATABASE", "employee_management");
        container.addEnv("MYSQL_ROOT_PASSWORD", "root");
        container.waitingFor(Wait.forListeningPort());
        container.start();
        return container;
    }

    private static GenericContainer<?> createMockContainer() {
        GenericContainer<?> container = new GenericContainer<>("iundarigun/mock-ws");
        container.withExposedPorts(1899);
        container.getPortBindings().add("3899:1899");
        container.waitingFor(Wait.forListeningPort());
        container.start();
        return container;
    }
}
