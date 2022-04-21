package com.rest.exception;

import com.rest.controller.ContaController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(assignableTypes = ContaController.class)
public class ContaExceptionHandler {

	@ExceptionHandler(Exception.class)
	public void defaultExceptionHandle(Exception ex) {
		log.error(ex.getMessage());
	}
}
