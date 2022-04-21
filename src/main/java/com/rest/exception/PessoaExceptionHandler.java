package com.rest.exception;

import com.rest.controller.PessoaController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(assignableTypes = PessoaController.class)
public class PessoaExceptionHandler {

	@ExceptionHandler(Exception.class)
	public void defaultExceptionHandle(Exception ex) {
		log.error(ex.getMessage());
	}
}
