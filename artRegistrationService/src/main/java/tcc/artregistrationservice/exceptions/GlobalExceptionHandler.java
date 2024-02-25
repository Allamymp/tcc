package tcc.artregistrationservice.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler  extends ResponseEntityExceptionHandler {
}
