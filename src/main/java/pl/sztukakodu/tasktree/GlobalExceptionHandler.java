package pl.sztukakodu.tasktree;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.sztukakodu.tasktree.exceptions.NotFoundException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity notFoundException(NotFoundException exception) {
        log.error(exception.getMessage(), exception);
        return ResponseEntity.status(NOT_FOUND).body(exception.getMessage());
    }
}
