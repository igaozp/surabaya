package xyz.andornot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import xyz.andornot.domain.Customer;
import xyz.andornot.repository.CustomerRepository;

@SpringBootApplication
public class JpaApplication {
    private static final Logger LOG = LoggerFactory.getLogger(JpaApplication.class);

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

            LOG.info("Customers found with findAll():");
            LOG.info("-------------------------------");
            repository.findAll().forEach(customer -> LOG.info(customer.toString()));

            var customer = repository.findById(1L);
            LOG.info("Customer found with findById(1L)");
            LOG.info("--------------------------------");
            LOG.info(customer.toString());

            LOG.info("Customer found with findByLastName('Bauer'):");
            LOG.info("--------------------------------------------");
            repository.findByLastName("Bauer").forEach(bauer -> LOG.info(bauer.toString()));
        };
    }
}