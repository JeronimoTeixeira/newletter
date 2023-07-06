package com.newsletter.newsletter.usecase;

import com.newsletter.newsletter.domain.dynamo.Client;
import com.newsletter.newsletter.domain.requests.RequestNews;
import com.newsletter.newsletter.services.WhatsAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WhatsAppUseCase {

    @Autowired
    private WhatsAppService whatsAppService;

    public void sendMessage(Client client, RequestNews requestNews){
        String message = "*" + requestNews.getTitle() + "* " + requestNews.getBody() + " " + requestNews.getFooter();
        whatsAppService.sendMessage(client.getPhone(), message);
    }



}
