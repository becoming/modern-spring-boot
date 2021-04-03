package tech.becoming.modernspringboot.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;
import tech.becoming.common.constants.HttpHeader;
import tech.becoming.common.exceptions.AbstractRuntimeException;

@Component
@RequiredArgsConstructor
public class ControllerHelper {

    private final ObjectMapper objectMapper;

    public ResponseEntity<String> toJson(Try<?> t) {

        return t.mapTry(objectMapper::writeValueAsString)
                .map(this::ok)
                .recover(AbstractRuntimeException.class, this::handleAbstractEx)
                .recover(Exception.class, this::handleGenericEx)
                .get();
    }

    private ResponseEntity<String> ok(String o) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(getJsonHeaders())
                .body(o);
    }
    private ResponseEntity<String> handleGenericEx(Exception e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .headers(getJsonHeaders())
                .body("An irrecoverable error occurred.");
    }

    private ResponseEntity<String> handleAbstractEx(AbstractRuntimeException e) {
        return ResponseEntity
                .status(e.getHttpCode())
                .headers(getJsonHeaders())
                .body(e.getMessage() + ", " + e.toString());
    }

    private HttpHeaders getJsonHeaders() {
        var h = new HttpHeaders();
        h.add(HttpHeader.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON_VALUE);
        return h;
    }
}
