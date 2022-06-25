package tech.becoming.modernspringboot.api;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import tech.becoming.common.exceptions.AbstractRuntimeException;

import java.time.Instant;

@ControllerAdvice
public class GenericExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {AbstractRuntimeException.class})
    protected ResponseEntity<Object> handleConflict(AbstractRuntimeException ex, WebRequest request) {
        String bodyOfResponse = ex.toString();
        var details = ErrorBody.builder()
                .timestamp(Instant.now())
                .message(ex.toString())
                .status(ex.getHttpCode())
                .error(ex.getMessage())
                .path(((ServletWebRequest) request).getRequest().getRequestURI())
                .build();

        var status = HttpStatus.resolve(details.getStatus());
        status = status != null ? status : HttpStatus.I_AM_A_TEAPOT;

        return handleExceptionInternal(ex, details, new HttpHeaders(), status, request);
    }

}

@Builder
@Getter
class ErrorBody {
    private Instant timestamp = Instant.now();
    private int status = 500;
    private String error = "Internal Server Error";
    private String message = "";
    private String path = "";
}

// {
//     "timestamp": "2021-04-03T18:41:16.587+00:00",
//     "status": 500,
//     "error": "Internal Server Error",
//     "message": "",
//     "path": "/robots/2"
// }
