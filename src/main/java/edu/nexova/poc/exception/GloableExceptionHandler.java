package edu.nexova.poc.exception;

import edu.nexova.poc.exception.response.Response;
import edu.nexova.poc.exception.settingException.InvalidSettingException;
import edu.nexova.poc.exception.settingException.SettingNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GloableExceptionHandler {

    @ExceptionHandler(SettingNotFoundException.class)
    public ResponseEntity<Response> handleSettingNotFoundException(SettingNotFoundException ex) {
        return new ResponseEntity(new Response(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidSettingException.class)
    public ResponseEntity<Response> handleInvalidSettingException(InvalidSettingException ex) {
        return new ResponseEntity(new Response(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return new ResponseEntity(new Response("Invalid field or something missing to fill"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Response> handleNotReadable(HttpMessageNotReadableException ex) {
        return new ResponseEntity(new Response("Port must be a positive number"), HttpStatus.BAD_REQUEST);
    }
}
