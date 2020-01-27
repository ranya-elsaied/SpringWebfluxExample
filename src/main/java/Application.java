import model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;
import reactor.core.publisher.Mono;
import repository.PersonRepository;

import javax.swing.text.Style;

//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableWebFlux
@EnableReactiveMongoRepositories("repository")
@EntityScan("model")
@ComponentScan({"controller", "service"})
public class Application implements CommandLineRunner  {

    @Autowired
    private PersonRepository personRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Person person = new Person(1, 50.0, 1.8);
        personRepository.save(person).block();
        person = new Person(2, 70.0, 1.8);
        personRepository.save(person).block();
        Mono<Person> mono = personRepository.findAllById(1);
        System.err.println("----"+mono.block());
        System.err.println("-----saved successful");
    }
}
