package service;

import model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import repository.PersonRepository;

/**
 * Service to calculate the BMIs
 */
@Service
public class BMIService {

    @Autowired
    private PersonRepository personRepository;

    public Flux<Double> calculateBMI(){
        return personRepository.findAll()
                .map(person -> person.getWeight()/(person.getHeight()*person.getHeight()))
                .log();
    }

    public Mono<Void> savePerson(Person person){
        return personRepository.save(person).then();
    }
}
