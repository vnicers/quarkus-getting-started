package org.acme.getting.started.common.event;

import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

/**
 * @author tam
 */
@Slf4j
@ApplicationScoped
public class TaskEventListener {
    void onTaskCompleted(@Observes TaskCompletedEvent taskCompletedEvent) {
        // ...log the task
        log.info("task completed param:{}", taskCompletedEvent.getParam());
    }
}
