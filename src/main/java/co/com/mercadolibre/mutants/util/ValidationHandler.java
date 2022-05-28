package co.com.mercadolibre.mutants.util;

import co.com.mercadolibre.mutants.exception.MutantException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Controlador de errores.
 */
@ControllerAdvice
public class ValidationHandler {

    @ExceptionHandler(WebExchangeBindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public List<FieldError> handleWebExchangeBindException(WebExchangeBindException webExchangeBindException) {

        return webExchangeBindException.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> new FieldError(fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());
    }

    @ExceptionHandler(value = {MutantException.class})
    @ResponseBody
    public ResponseEntity<Object> handleApplicationException(MutantException mutantException) {
        MutantError error = new MutantError(mutantException.getStatus(),
                mutantException.getErrorMessage(), mutantException.getDetailMessage(), mutantException);
        return buildResponseEntity(error);
    }

    private ResponseEntity<Object> buildResponseEntity(MutantError mutantError) {
        return new ResponseEntity<>(mutantError, mutantError.getStatus());
    }

}