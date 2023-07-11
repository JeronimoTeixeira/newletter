package com.newsletter.newsletter.services;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AmazonDynamoDBException;
import com.newsletter.newsletter.domain.dynamo.Client;
import org.springframework.stereotype.Service;

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
            throw exception;
        }
    }

    public List<Client> getClients(){
        return dynamoDBMapper.scan(Client.class, new DynamoDBScanExpression());
    }
}
