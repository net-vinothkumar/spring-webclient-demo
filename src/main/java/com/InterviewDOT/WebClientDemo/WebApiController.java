package com.InterviewDOT.WebClientDemo;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.List;

import static org.springframework.http.HttpMethod.GET;

@RestController
public class WebApiController {

    @GetMapping("/toys-blocking")
    public List<Toy> getToysDataBlocking() {
        /** FIRST **/
        System.out.println("Starting BLOCKING CONTROLLER..");

        /** SECOND **/
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Toy>> response = restTemplate.exchange(
                "http://localhost:8080/slow-service-toys",
                GET,
                null,
                new ParameterizedTypeReference<List<Toy>>() {
                });

        List<Toy> result = response.getBody();
        result.forEach(toy -> System.out.println(toy.toString()));
        /** THIRD **/
        System.out.println("Exiting BLOCKING CONTROLLER..");
        return result;
    }

    @GetMapping(value = "/toys-non-blocking", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Toy> getToysDataNonBlocking() {
        /** FIRST **/
        System.out.println("Starting NON-BLOCKING CONTROLLER..");

        Flux<Toy> toyFlux = WebClient.create()
                .get()
                .uri("http://localhost:8080/slow-service-toys")
                .retrieve()
                .bodyToFlux(Toy.class);

        /** THIRD **/
        toyFlux.subscribe(toy -> System.out.println(toy.toString()));

        /** SECOND **/
        System.out.println("Exiting NON-BLOCKING CONTROLLER..");
        return toyFlux;
    }
}
