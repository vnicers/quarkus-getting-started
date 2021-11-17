package org.acme.getting.started.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author tam
 */
@AllArgsConstructor
public class ServiceException extends RuntimeException{

    @Getter
    private Integer code;

    @Getter
    private String message;


}
