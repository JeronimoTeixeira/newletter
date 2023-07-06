package com.newsletter.newsletter.services;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.*;
import com.newsletter.newsletter.domain.constants.CommonConstants;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {
    private final AmazonSQS sqsClient;

    public NewsService(){
        this.sqsClient = AmazonSQSClientBuilder.standard()
                .withRegion(CommonConstants.REGION_AWS)
                .build();
    }

    public void insertNews(String serializedObject){
        try{
            SendMessageRequest sendMessageRequest = new SendMessageRequest()
                    .withQueueUrl(CommonConstants.URL_QUEUE)
                    .withMessageBody(serializedObject)
                    .withMessageGroupId("news-letter")
                    ;
            sqsClient.sendMessage(sendMessageRequest);

        }
        catch (AmazonSQSException exception){
            System.err.println(exception.getMessage());
        }

    }

    public List<Message> getNews(){
        try{
            ReceiveMessageResult receiveMessageResult = sqsClient.receiveMessage(
                    new ReceiveMessageRequest()
                            .withQueueUrl(CommonConstants.URL_QUEUE)
                            .withMaxNumberOfMessages(10)
            );
            return receiveMessageResult.getMessages();
        }
        catch (AmazonSQSException exception){
//            S
        }

        return null;
    }

    public void deleteNews(Message message){
        try{
            String messageReceiptHandle = message.getReceiptHandle();
            sqsClient.deleteMessage(
                new DeleteMessageRequest()
                        .withQueueUrl(CommonConstants.URL_QUEUE)
                        .withReceiptHandle(messageReceiptHandle)
            );

//            sqsClient.getQueueAttributes()

        }
        catch (AmazonSQSException exception){
            throw exception;
        }

    }



}
