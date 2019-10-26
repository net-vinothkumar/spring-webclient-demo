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

@RestController
public class WebApiController {

    private static final int DEFAULT_PORT = 8080;

    private int serverPort = DEFAULT_PORT;

    @GetMapping("/toys-blocking")
    public List<Toy> getToysDataBlocking() {
        System.out.println("Starting BLOCKING CONTROLLER..");
        final String uri = getSlowServiceUri();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Toy>> response = restTemplate.exchange(
                uri, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Toy>>() {
                });

        List<Toy> result = response.getBody();
        result.forEach(toy -> System.out.println(toy.toString()));
        System.out.println("Exiting BLOCKING CONTROLLER..");
        return result;
    }

    @GetMapping(value = "/toys-non-blocking", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Toy> getToysDataNonBlocking() {
        System.out.println("Starting NON-BLOCKING CONTROLLER..");
        Flux<Toy> toyFlux = WebClient.create()
                .get()
                .uri(getSlowServiceUri())
                .retrieve()
                .bodyToFlux(Toy.class);

        toyFlux.subscribe(toy -> System.out.println(toy.toString()));
        System.out.println("Exiting NON-BLOCKING CONTROLLER..");
        return toyFlux;
    }

    private String getSlowServiceUri() {
        return "http://localhost:" + serverPort + "/slow-service-toys";
    }
}
