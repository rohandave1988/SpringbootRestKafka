package com.rest.kafka.consumer;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.kafka.model.Account;

@Service
public class AccountConsumer {
	
	private static final Logger logger = LoggerFactory.getLogger(AccountConsumer.class);

    @KafkaListener(topics = "accountTopic", group = "groupjson", containerFactory = "kafkaListenerContainerFactory")
    public void consumemessage(Account account) throws JsonProcessingException{
    	ObjectMapper mapper = new ObjectMapper();
		String accountDetails = mapper.writeValueAsString(account);
		logger.info("Json message received using Kafka listener " + accountDetails);
    }
}
