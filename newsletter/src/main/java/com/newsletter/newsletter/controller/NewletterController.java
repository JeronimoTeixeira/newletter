package com.newsletter.newsletter.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.newsletter.newsletter.domain.requests.RequestNews;
import com.newsletter.newsletter.domain.requests.RequestRegister;
import com.newsletter.newsletter.usecase.NewsUseCase;
import com.newsletter.newsletter.usecase.RegisterContactUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class NewletterController {


    @Autowired
    private RegisterContactUseCase registerContactUseCase;

    @Autowired
    private NewsUseCase newsUseCase;


    @PostMapping("/register")
    private ResponseEntity<String> registerEmail(@RequestBody RequestRegister request){
        registerContactUseCase.register(request);
        return new ResponseEntity<String>("Cadastrado com sucesso", HttpStatus.OK);
    }

    @PostMapping("/news")
    private ResponseEntity<String> createNews(@RequestBody RequestNews request) throws JsonProcessingException {
        newsUseCase.createNews(request);
        return new ResponseEntity<String>("Cadastrado com sucesso", HttpStatus.OK);
    }

    @GetMapping("/news")
    private ResponseEntity<List<RequestNews>> getNews() throws JsonProcessingException{
        return new ResponseEntity<List<RequestNews>>(newsUseCase.getNews(), HttpStatus.OK);
    }


}
