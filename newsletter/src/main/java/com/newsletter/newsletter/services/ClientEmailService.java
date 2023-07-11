package com.newsletter.newsletter.services;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AmazonDynamoDBException;
import com.newsletter.newsletter.domain.constants.CommonMessage;
import com.newsletter.newsletter.domain.dynamo.Client;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ClientEmailService {

    private DynamoDBMapper dynamoDBMapper;
    public ClientEmailService(){
        AmazonDynamoDB dynamoDBClient = AmazonDynamoDBClientBuilder.defaultClient();
        dynamoDBMapper = new DynamoDBMapper(dynamoDBClient);
    }

    public void insertClient(Client contactClient){
        try {
            dynamoDBMapper.save(contactClient);
        }
        catch (AmazonDynamoDBException exception) {
            switch (exception.getStatusCode()){
                case 400:
                    throw new ResponseStatusException(HttpStatusCode.valueOf(exception.getStatusCode()), CommonMessage.ERRO_DYNAMO_DB_GENERIC);
                default:
                    throw new ResponseStatusException(HttpStatusCode.valueOf(exception.getStatusCode()), CommonMessage.ERRO_DYNAMO_DB_NOT_FOUND);
            }
        }
    }

    public List<Client> getClients(){
        return dynamoDBMapper.scan(Client.class, new DynamoDBScanExpression());
    }
}
