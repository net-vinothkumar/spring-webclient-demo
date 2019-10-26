package com.InterviewDOT.WebClientDemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class ToysSlowServiceController {

    @GetMapping("/slow-service-toys")
    private List<Toy> getAllToys() throws Exception {
        Thread.sleep(2000L);         /** DELAY **/
        return Arrays.asList(
                new Toy("toy-id-1", "Electric Train", 2045),
                new Toy("toy-id-2", "Remote Car", 5688),
                new Toy("toy-id-3", "Barbie set", 4889),
                new Toy("toy-id-4", "Remote Helicopter", 8923));
    }
}
