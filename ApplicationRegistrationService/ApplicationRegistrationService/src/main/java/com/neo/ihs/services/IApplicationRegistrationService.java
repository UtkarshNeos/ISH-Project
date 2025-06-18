package com.neo.ihs.services;

import com.neo.ihs.binding.CitizenAppRegistrationInputs;
import com.neo.ihs.exception.InvalidSSNException;
import reactor.core.publisher.Mono;

public interface IApplicationRegistrationService {
    Mono<Integer> registerCitizenApplication(CitizenAppRegistrationInputs inputs) throws InvalidSSNException;
}
