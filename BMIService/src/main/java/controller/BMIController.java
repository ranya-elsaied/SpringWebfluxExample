package controller;

import model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Double> getBMI(){
        return bmiService.calculateBMI();
    }

    @GetMapping(value = "/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Long> getEvents(){
        return Flux.interval(Duration.ofSeconds(1));
    }

    @PostMapping("/person")
    ResponseEntity<Mono<Void>> create(@RequestBody Person person) {
        return ResponseEntity.ok(bmiService.savePerson(person));
    }

}
