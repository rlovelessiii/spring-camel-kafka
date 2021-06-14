package com.stackleader.service;

import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static com.stackleader.routes.Producer.DIRECT_LOGGER;

@Service
public class MessageService {

    private static final Logger LOG = LoggerFactory.getLogger(MessageService.class);

    @Produce(uri = DIRECT_LOGGER)
    private static ProducerTemplate directLogger;

    public static void sendMessageToTopic(String message) {
        if (message.length() == 0) {
            throw new IllegalArgumentException("Empty Message");
        }
        LOG.info(message);
        directLogger.sendBody(message);
    }

}
