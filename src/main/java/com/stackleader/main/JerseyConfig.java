package com.stackleader.main;

import com.stackleader.api.Controller;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(Controller.class);
    }
}
