package com.stackleader.routes;

import javax.ws.rs.BadRequestException;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.kafka.KafkaConstants;
import static org.apache.camel.builder.endpoint.StaticEndpointBuilders.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Producer extends RouteBuilder {

    private static final Logger LOG = LoggerFactory.getLogger(Producer.class);
    public static final String DIRECT_LOGGER = "direct:start";
    public static final String KAFKA_TOPIC = "kafka:quickstart-events";
    
    

    @Override
    public void configure() throws Exception {
        onException(RuntimeException.class)
                .handled(true)
                .setBody(constant("ERROR HANDLED"))
                .log("Error thrown: ${body}");

        from(DIRECT_LOGGER)
                .onException(BadRequestException.class)
                .handled(false)
                .setBody(constant("ALT ERROR HANDLED"))
                .log("Error thrown: ${body}")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        throw new BadRequestException();
                    }
                }).end()
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        String message = exchange.getIn().getBody(String.class);
                        if (message.equalsIgnoreCase("error")) {
                            throw new BadRequestException();
                        } else if (message.equalsIgnoreCase("illegal")) {
                            throw new IllegalArgumentException();
                        }
                    }
                })
                .setHeader(KafkaConstants.KEY, constant("Camel"))
                .to(kafka("quickstart-events"));
    }

}
