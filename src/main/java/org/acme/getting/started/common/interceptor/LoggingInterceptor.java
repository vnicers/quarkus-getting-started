package org.acme.getting.started.common.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.acme.getting.started.common.annotation.Logged;

import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.time.Duration;
import java.time.Instant;

@Logged
@Slf4j
@Priority(2020)
@Interceptor
public class LoggingInterceptor {

    @AroundInvoke
    Object logInvocation(InvocationContext context) {
        // ...log before
        Instant start = Instant.now();
        log.info("invoke before class:{} method:{}", context.getTarget().getClass(), context.getMethod().getName());
        Object ret = null;
        try {
            ret = context.proceed();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            log.info("invoke after class:{} method:{} result:{}", context.getTarget().getClass(), context.getMethod().getName(), ret);
            log.info("time consuming:{}", Duration.between(start, Instant.now()).toMillis());
        }
        // ...log after
        return ret;
    }

}