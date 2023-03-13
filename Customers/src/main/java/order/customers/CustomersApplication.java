package order.customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpHeaders;

/**
 * This class is responsible for bootstrapping the Spring Boot application and running it. It also includes configuration
 * for enabling JPA repositories and scanning for entities in the "order.customers" package.
 * The CustomersApplication class implements the CommandLineRunner interface and overrides its run() method to call the
 * printOrders() method of the OrderController class.
 */
@SpringBootApplication
@EnableJpaRepositories(basePackages = "order.customers")
@EntityScan(basePackages = "order.customers")
@RestController
@RequestMapping("/api")
public class CustomersApplication implements CommandLineRunner  {

    @Autowired
    private OrderController orderController;


    public static void main(String[] args) {
        SpringApplication.run(CustomersApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        orderController.printOrders();

    }

}





