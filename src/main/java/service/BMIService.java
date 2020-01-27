package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
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
}
