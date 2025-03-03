package crudGerenciamentoDeRecursos.crud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnsupportedProductOperationsException extends RuntimeException {
	public UnsupportedProductOperationsException(String message) {
        super(message);
    }
}
