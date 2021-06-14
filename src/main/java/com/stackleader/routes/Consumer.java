package com.stackleader.routes;

import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static com.stackleader.routes.Producer.KAFKA_TOPIC;

@Component
public class Consumer extends RouteBuilder {

    private static final Logger LOG = LoggerFactory.getLogger(Consumer.class);

    @Override
    public void configure() throws Exception {
        try {
            from(KAFKA_TOPIC)
                    .log("Message received from Kafka: ${body}");
        }
        catch(Exception e) {
            LOG.error(e.toString());
        }
    }

}
