package com.sourav.dice;

import com.sourav.dice.domain.AppJavaBasedConfig;
import com.sourav.dice.domain.ApplicationApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public class Main {

    public static void main(String[] args) {
        log.info("Main method for roll the dice problem..");
        ApplicationContext context = new AnnotationConfigApplicationContext(AppJavaBasedConfig.class);
        ApplicationApi api = context.getBean(ApplicationApi.class);
        api.run(context, args);
        ((AnnotationConfigApplicationContext) context).close();
    }
}
