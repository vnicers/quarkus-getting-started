package org.acme.getting.started.common.config;

import io.smallrye.config.WithDefault;

/**
 * @author tam
 */
public interface ApiRetryConfig {
    @WithDefault("5000")
    Long delay();

    @WithDefault("10000")
    Long maxDelay();

    @WithDefault("3")
    Integer multiplier();

    @WithDefault("3")
    Integer maxAttempts();
}
