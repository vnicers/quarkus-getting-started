package org.acme.getting.started;

import io.quarkus.test.junit.QuarkusTest;
import lombok.extern.slf4j.Slf4j;
import org.acme.getting.started.common.config.ServiceApiConfig;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

/**
 * @author tam
 */
@Slf4j
@QuarkusTest
public class ConfigPropertiesTest {

    @Inject
    ServiceApiConfig serviceApiConfig;

    @Test
    public void testApiConfig() {
        log.info("serviceApiConfig:{}", serviceApiConfig);
    }

    @Test
    public void testClassLoader() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class<?> aClass = Class.forName("org.acme.getting.started.service.GreetingService");
        Class<?> aClass1 = ClassLoader.getSystemClassLoader().loadClass("org.acme.getting.started.service.GreetingService");
        log.info("before new instance");
        Object o = aClass1.newInstance();

    }
}
