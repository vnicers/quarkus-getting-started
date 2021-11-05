package org.acme.getting.started.service;

import lombok.extern.slf4j.Slf4j;
import org.acme.getting.started.common.annotation.Logged;
import org.acme.getting.started.common.event.TaskCompletedEvent;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;

/**
 * @author tam
 */
@Slf4j
@ApplicationScoped
public class GreetingService {

    static {
        log.info("static -------------------------");
    }

    @ConfigProperty(name = "config.greeting.message")
    String message;

    @Inject
    Event<TaskCompletedEvent> event;

    @Logged
    public String greeting(String name) {
        event.fire(new TaskCompletedEvent(name));
        return "hello" + name + "--> " + message;
    }

    @PostConstruct
    void init() {
        log.info("greeting service bean init ");
    }

    @PreDestroy
    void destroy() {
        log.info("greeting service bean destroy ");
    }
}
