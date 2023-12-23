package xyz.andornot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import xyz.andornot.domain.Customer;
import xyz.andornot.repository.CustomerRepository;

@Slf4j
@SpringBootApplication
public class JpaApplication {
    public static void main(String[] args) {
        SpringApplication.run(JpaApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(CustomerRepository repository) {
        return args -> {
            repository.save(new Customer("Jack", "Bauer"));
            repository.save(new Customer("Chole", "O'Brain"));
            repository.save(new Customer("Kim", "Bauer"));
            repository.save(new Customer("David", "Palmer"));
            repository.save(new Customer("Michelle", "Dessler"));

            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            repository.findAll().forEach(customer -> log.info(customer.toString()));

            var customer = repository.findById(1L);
            log.info("Customer found with findById(1L)");
            log.info("--------------------------------");
            log.info("{}", customer);

            log.info("Customer found with findByLastName('Bauer'):");
            log.info("--------------------------------------------");
            repository.findByLastName("Bauer").forEach(bauer -> log.info(bauer.toString()));
        };
    }
}