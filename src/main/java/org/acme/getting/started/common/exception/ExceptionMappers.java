package org.acme.getting.started.common.exception;

import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
public class ExceptionMappers {

    @ServerExceptionMapper
    public RestResponse<ErrorInfo> serviceException(HttpServerRequest httpServerRequest, ContainerRequestContext requestContext, ServiceException ex) throws IOException {
        ErrorInfo errorInfo = new ErrorInfo(ex.getCode(), ex.getMessage());
        log.info("service exception error info:{}",errorInfo);
        return RestResponse.status(Response.Status.BAD_REQUEST,errorInfo);
    }
}