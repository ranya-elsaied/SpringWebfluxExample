package controller;

import model.Person;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import repository.PersonRepository;
import service.BMIService;

import java.time.Duration;

/**
 * API to calculate the BMI
 */
@RestController
@RequestMapping("bmi")
public class BMIController {
    @Autowired
    private BMIService bmiService;

    @Autowired
    private PersonRepository repository;

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Double> getBMI(){
        return bmiService.calculateBMI();
    }

    @GetMapping(value = "/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Long> getEvents(){
        return Flux.interval(Duration.ofSeconds(1));
    }

    @PostMapping("/person")
    Mono<Void> create(@RequestBody Person person) {
        return this.repository.save(person).then();
    }

}
