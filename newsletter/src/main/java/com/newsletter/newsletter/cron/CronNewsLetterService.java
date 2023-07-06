package com.newsletter.newsletter.cron;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.newsletter.newsletter.domain.dynamo.Client;
import com.newsletter.newsletter.domain.requests.RequestNews;
import com.newsletter.newsletter.services.ClientEmailService;
import com.newsletter.newsletter.usecase.NewsUseCase;
import com.newsletter.newsletter.usecase.RegisterUseCase;
import com.newsletter.newsletter.usecase.WhatsAppUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CronNewsLetterService {

    @Autowired
    private ClientEmailService clientEmailService;

    @Autowired
    private NewsUseCase newsUseCase;

    @Autowired
    private RegisterUseCase registerUseCase;

    @Autowired
    private WhatsAppUseCase whatsAppUseCase;

    @Scheduled(fixedRate = 20000)
    public void sendNewsForClientsCron() throws JsonProcessingException {
        List<Client> clients = clientEmailService.getClients();
        List<RequestNews> requestsNews = newsUseCase.getNews();
        for (Client client : clients){
            sendNewsForClient(requestsNews, client);
        }

    }

    private void sendNewsForClient(List<RequestNews> requestsNews, Client client){
        for (RequestNews requestNews : requestsNews){
            whatsAppUseCase.sendMessage(client, requestNews);
        }
    }
}
