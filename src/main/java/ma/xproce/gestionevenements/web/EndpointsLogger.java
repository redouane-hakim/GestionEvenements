package ma.xproce.gestionevenements.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Component
public class EndpointsLogger implements ApplicationRunner {
    @Autowired
    private RequestMappingHandlerMapping mapping;

    @Override
    public void run(ApplicationArguments args) {
        mapping.getHandlerMethods().forEach((k, v) -> System.out.println("MAPPING: " + k + " -> " + v));
    }
}