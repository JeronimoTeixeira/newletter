package com.newsletter.newsletter.services;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.AmazonDynamoDBException;
import com.newsletter.newsletter.domain.dynamo.Client;
import org.springframework.stereotype.Service;

@Service
public class ClientEmailService {

    public void insertClient(Client contactClient){
        final AmazonDynamoDB dynamoDBClient = AmazonDynamoDBClientBuilder.defaultClient();
        final DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(dynamoDBClient);

        try {
            dynamoDBMapper.save(contactClient);
        }
        catch (AmazonDynamoDBException exception) {
            System.err.println(exception.getMessage());
        }
    }
}
