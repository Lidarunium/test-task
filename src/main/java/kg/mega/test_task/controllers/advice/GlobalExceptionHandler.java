package kg.mega.test_task.controllers.advice;

import kg.mega.test_task.exceptions.NotFoundException;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException ex) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorResponse errorResponse = new ErrorResponse(status.value(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, status);
    }

    @Data
    private static class ErrorResponse {
        private final LocalDateTime timestamp;
        private final int status;
        private final String message;

        public ErrorResponse(int status, String message) {
            this.timestamp = LocalDateTime.now();
            this.status = status;
            this.message = message;
        }
    }
}
