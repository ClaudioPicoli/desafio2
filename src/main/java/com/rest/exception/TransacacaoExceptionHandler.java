package com.rest.exception;

import com.rest.controller.TransacaoController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(assignableTypes = TransacaoController.class)
public class TransacacaoExceptionHandler {

	@ExceptionHandler(Exception.class)
	public void defaultExceptionHandle(Exception ex) {
		log.error(ex.getMessage());
	}
}
