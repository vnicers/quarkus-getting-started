package org.acme.getting.started;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import lombok.extern.slf4j.Slf4j;
import org.jboss.logging.Logger;

@Slf4j
@ApplicationScoped
public class AppLifecycleBean {


    void onStart(@Observes StartupEvent ev) {               
        log.info("The application is starting...");
    }

    void onStop(@Observes ShutdownEvent ev) {
        log.info("The application is stopping...");
    }

}