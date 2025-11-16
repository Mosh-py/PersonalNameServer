package com.moshood.web.controller.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MappedControllerAdvice {

	@ExceptionHandler(Exception.class)
	public String handleException() {
		return "redirect:/";
	}
	
}
