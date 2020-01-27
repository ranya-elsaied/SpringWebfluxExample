package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import service.BMIService;

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
//        return  Flux.fromIterable(bmiService.calculateBMI());
        return bmiService.calculateBMI();
    }

}
