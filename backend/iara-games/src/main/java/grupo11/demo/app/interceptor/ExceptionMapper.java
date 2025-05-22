package grupo11.demo.app.interceptor;
import grupo11.demo.domain.exception.WebException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionMapper {

    @ExceptionHandler(WebException.class)
    public ResponseEntity<Map<String, Object>> handleWebException(WebException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("error", ex.getMessage());

        return ResponseEntity
                .status(ex.getStatus())
                .body(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleOtherExceptions(Exception ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("error", "Erro interno no servidor");

        return ResponseEntity
                .status(500)
                .body(body);
    }
}
