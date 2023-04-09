package br.com.alkimin.apiclinicamedica.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class ExceptionHandlerClinicaMedica {

    @ExceptionHandler({EntityNotFoundException.class, NoSuchElementException.class})
    public ResponseEntity exceptionHandler404(){
        return ResponseEntity.notFound().build();
    }

   @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity exceptionHandler400(MethodArgumentNotValidException exception){
        var errors = exception.getFieldErrors();
        return ResponseEntity.badRequest().body(errors.stream().map(DadosErroValidacao::new).toList());
    }

    private record DadosErroValidacao(String campo, String mensagem){
        public DadosErroValidacao(FieldError fieldError) {
            this(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }
}
