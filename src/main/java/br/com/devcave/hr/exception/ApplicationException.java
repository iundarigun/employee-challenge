package br.com.devcave.hr.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApplicationException extends RuntimeException {

    private final HttpStatus status;

    public ApplicationException(final HttpStatus httpStatus, final String message) {
        super(message);
        this.status = httpStatus;
    }
}
