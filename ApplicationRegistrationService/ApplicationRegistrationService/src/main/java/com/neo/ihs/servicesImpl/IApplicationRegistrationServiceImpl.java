package com.neo.ihs.servicesImpl;

import com.neo.ihs.binding.CitizenAppRegistrationInputs;
import com.neo.ihs.entities.CitizenApplicationEntity;
import com.neo.ihs.exception.InvalidSSNException;
import com.neo.ihs.repositories.IApplicationRegistrationRepository;
import com.neo.ihs.services.IApplicationRegistrationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class IApplicationRegistrationServiceImpl implements IApplicationRegistrationService {

    @Autowired
    private WebClient webClient;

    @Autowired
    private IApplicationRegistrationRepository citzenRepo;

    @Value("${ar.ssa-web.url}")
    private String endpointUrl;

    @Value("${ar.state}")
    private String targetState;

    @Override
    public Mono<Integer> registerCitizenApplication(CitizenAppRegistrationInputs inputs) {

        if (inputs == null || inputs.getSsn() == null) {
            System.out.println("Invalid input or SSN is missing.");
            return Mono.just(0);
        }

        System.out.println("Calling SSA API with SSN: " + inputs.getSsn());
        String url = endpointUrl.replace("{ssn}", inputs.getSsn().toString());

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .map(String::trim)
                .flatMap(stateName -> {
                    System.out.println("SSA API returned state: " + stateName);

                    if (stateName.equalsIgnoreCase(targetState)) {
                        CitizenApplicationEntity entity = new CitizenApplicationEntity();
                        BeanUtils.copyProperties(inputs, entity);
                        entity.setStateName(stateName);

                        // Simulate async DB save (for real reactive repo, use ReactiveCrudRepository)
                        int appId = citzenRepo.save(entity).getAppId();
                        System.out.println("Application registered with ID: " + appId);
                        return Mono.just(appId);
                    } else {
                        System.out.println("Citizen does not belong to target state: " + targetState);
                        return Mono.just(0);
                    }
                })
                .onErrorResume(ex -> {
                    System.err.println("Exception while calling SSA API: " + ex.getMessage());
                    return Mono.just(0);
                });
    }
}
