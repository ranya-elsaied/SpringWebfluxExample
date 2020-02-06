package client;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BMIServiceClient {

    private WebClient client = WebClient.create("http://localhost:8080");

    private Flux<Double> result = client.get()
            .uri("/bmi/")
            .accept(MediaType.TEXT_EVENT_STREAM)
            .retrieve()
            .bodyToFlux(Double.class)
            .take(5);

    public String getResult() {
        Mono<List<Double>>res = result.collect(Collectors.toList());
        return ">> result = " + res.block();
    }
}

