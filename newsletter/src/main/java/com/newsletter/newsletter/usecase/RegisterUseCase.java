package com.newsletter.newsletter.usecase;

import com.newsletter.newsletter.domain.dynamo.Client;
import com.newsletter.newsletter.domain.requests.RequestRegister;
import com.newsletter.newsletter.services.ClientEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegisterUseCase {

    @Autowired
    private ClientEmailService clientEmailService;

    public void register(RequestRegister requestRegister){
        Client client = Client.builder()
                .name(requestRegister.getName())
                .email(requestRegister.getEmail())
                .phone(requestRegister.getTelephone())
                .build();
        clientEmailService.insertClient(client);
    }
}
