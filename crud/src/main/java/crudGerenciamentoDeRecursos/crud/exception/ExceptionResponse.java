package crudGerenciamentoDeRecursos.crud.exception;

import java.util.Date;

public record ExceptionResponse(Date timestamp, String message, String detail) {}
