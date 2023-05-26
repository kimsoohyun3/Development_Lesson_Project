package project.lesson.exception.common;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import project.lesson.exception.ExceptionEnum;
import project.lesson.exception.ExceptionMessage;

@RestControllerAdvice
public class CResourceNotExistExceptionHandler {
    @ExceptionHandler(CResourceNotExistException.class)
    public ResponseEntity<ExceptionMessage> handleResourceNotExistException(CResourceNotExistException e) {
        ExceptionMessage exceptionMessage = new ExceptionMessage(
                ExceptionEnum.RESOURCE_NOT_EXIST.getCode(), e.getMessage(),
                ExceptionEnum.RESOURCE_NOT_EXIST.getMessage()
        );
        return ResponseEntity.badRequest().body(exceptionMessage);
    }
}