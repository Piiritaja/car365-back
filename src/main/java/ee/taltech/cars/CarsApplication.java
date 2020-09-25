package ee.taltech.cars;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@EnableJpaRepositories
@SpringBootApplication
public class CarsApplication{
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jdbc:postgresql://localhost:5432/");
    public static void main(String[] args) {
        SpringApplication.run(CarsApplication.class, args);
    }


}
