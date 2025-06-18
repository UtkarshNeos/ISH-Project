package com.neo.ihs.controllers;

import com.neo.ihs.binding.CitizenAppRegistrationInputs;
import com.neo.ihs.exception.InvalidSSNException;
import com.neo.ihs.services.IApplicationRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/CitizenAR-api")
public class CitizenApplicationRegistrationOperationsController {

    @Autowired
    private IApplicationRegistrationService registrationService;

    @PostMapping("/save")
    public Mono<ResponseEntity<String>> saveCitizenApplication(@RequestBody CitizenAppRegistrationInputs inputs) throws InvalidSSNException {
        return registrationService.registerCitizenApplication(inputs)
                .map(appId -> {
                    if (appId > 0) {
                        return new ResponseEntity<>("Citizen Application is registered with the ID: " + appId, HttpStatus.CREATED);
                    } else {
                        return new ResponseEntity<>("Invalid SSN or Citizen must belong to California state", HttpStatus.BAD_REQUEST);
                    }
                })
                .onErrorResume(e -> Mono.just(new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST)));
    }
}
