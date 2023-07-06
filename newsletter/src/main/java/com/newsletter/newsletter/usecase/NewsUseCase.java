package com.newsletter.newsletter.usecase;

import com.amazonaws.services.sqs.model.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.newsletter.newsletter.domain.requests.RequestNews;
import com.newsletter.newsletter.services.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class NewsUseCase {

    @Autowired
    private NewsService newsService;

    public void createNews(RequestNews requestNews) throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        String serializedObject = objectMapper.writeValueAsString(requestNews);;
        newsService.insertNews(serializedObject);
    }

    public List<RequestNews> getNews() throws JsonProcessingException{
        List<Message> messages = newsService.getNews();
        List<RequestNews> listRequestNews = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        for (Message message : messages) {
            String body = message.getBody();
            RequestNews request = objectMapper.readValue(body, RequestNews.class);
            listRequestNews.add(request);
        }
        return listRequestNews;
    }

}
