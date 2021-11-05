package org.acme.getting.started.common.config;

import io.smallrye.config.ConfigMapping;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author tam
 */
@ConfigMapping(prefix = "service.config.api",
        namingStrategy = ConfigMapping.NamingStrategy.VERBATIM)
public interface ServiceApiConfig {
    @NotEmpty
    String url();

    @NotEmpty
    String apiKey();

    @NotEmpty
    String secret();

    @NotNull
    @Max(10000)
    Integer connectTimeout();

    @NotNull
    @Max(60000)
    Integer socketTimeout();

    @NotNull
    ApiRetryConfig retry();
}
