package com.newsletter.newsletter.services;

import com.newsletter.newsletter.domain.constants.CommonConstants;
import org.springframework.stereotype.Service;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
@Service
public class WhatsAppService {

    public static final String ACCOUNT_SID = "AC32ca856e945bc0a545c5853c6570c2ba";
    public static final String AUTH_TOKEN = "a0ee4bb5bcf05d15bc068c622645282f";

    public WhatsAppService(){
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }

    public void sendMessage(String phone, String messageWhatsApp){
        Message message = Message.creator(
                        new PhoneNumber(CommonConstants.PREFIX_WPP + phone),
                        new PhoneNumber(CommonConstants.PREFIX_WPP + CommonConstants.PHONE_TWILLIO),
                        messageWhatsApp)
                .create();
    }


}
