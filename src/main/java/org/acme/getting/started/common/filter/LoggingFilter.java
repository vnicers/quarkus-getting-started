package org.acme.getting.started.common.filter;

import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerRequest;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Provider
public class LoggingFilter implements ContainerRequestFilter, ContainerResponseFilter {

    @Context
    HttpServerRequest request;

    @SneakyThrows
    @Override
    public void filter(ContainerRequestContext context) {
        log.info(">>> request {} {} from IP {} params:{}", request.method(), request.absoluteURI(), request.remoteAddress(), request.params());

        if (request.method() == HttpMethod.POST) {
            String requestBodyStr = IOUtils.toString(context.getEntityStream(), StandardCharsets.UTF_8);
            log.info(">>> request body {}", requestBodyStr);
            context.setEntityStream(new ByteArrayInputStream(requestBodyStr.getBytes(StandardCharsets.UTF_8)));
        }
    }

    @Override
    public void filter(ContainerRequestContext containerRequestContext, ContainerResponseContext containerResponseContext) throws IOException {
        Object entity = containerResponseContext.getEntity();
        log.info("<<< response body {}", entity);
    }
}