package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
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

    @GetMapping
    public Flux<Double> getBMI(){
        return bmiService.calculateBMI();
    }

    @GetMapping(value = "/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Long> getEvents(){
        return Flux.interval(Duration.ofSeconds(1));
    }

}
