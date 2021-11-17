package org.acme.getting.started.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author tam
 */
@Data
@AllArgsConstructor
public class ErrorInfo {

    private Integer errorCode;

    private String errorMessage;

}
