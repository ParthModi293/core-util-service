package com.clapcle.core.globalException;

import com.clapcle.core.common.ConstCore;
import com.clapcle.core.exception.FileNotFoundException;
import com.clapcle.core.common.ResponseBean;
import com.clapcle.core.exception.ValidationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ResponseBean<Map<String, String>>> handleValidationException(ValidationException ex) {

        ResponseBean<Map<String, String>> response = new ResponseBean<>();
        response.setRCode(ex.getRCode());
        response.setRStatus(HttpStatus.valueOf(ex.getRStatus().value()));
        response.setDisplayMessage(ex.getDisplayMessage());
        response.setRMsg(ex.getRMsg());

        return new ResponseEntity<>(response, ex.getRStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseBean<Map<String, String>>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();

        Map<String, String> errors = new HashMap<>();
        for (FieldError error : bindingResult.getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        ResponseBean<Map<String, String>> errorResponse = new ResponseBean<>();
        errorResponse.setRStatus(HttpStatus.BAD_REQUEST);
        errorResponse.setRMsg("Validation Failed");
        errorResponse.setRCode(ConstCore.rCode.BAD_REQUEST);
        errorResponse.setDisplayMessage(errors.toString());

        return new ResponseEntity<>(errorResponse, errorResponse.getRStatus());
    }


    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<Object> handleFileNotFoundException(FileNotFoundException ex) {

        ResponseBean<Map<String, String>> response = new ResponseBean<>();
        response.setRCode(ex.getRCode());
        response.setRStatus(HttpStatus.valueOf(ex.getRStatus().value()));
        response.setDisplayMessage(ex.getDisplayMessage());
        response.setRMsg(ex.getRMsg());

        return new ResponseEntity<>(response, ex.getRStatus());
    }
}
