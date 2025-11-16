package com.moshood.shell.exceptions;

import java.util.NoSuchElementException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.shell.command.CommandExceptionResolver;
import org.springframework.shell.command.CommandHandlingResult;

public class CustomExceptionResolver implements CommandExceptionResolver {

	@Override
	public CommandHandlingResult resolve(Exception ex) {
		// TODO Auto-generated method stub
		if (ex instanceof DataIntegrityViolationException ) {
			return CommandHandlingResult.of("The alias is already taken \n");
		} else if(ex instanceof NoSuchElementException) {
			return  CommandHandlingResult.of( " alias doesn't exist \n");
		}
		ex.printStackTrace();
		return CommandHandlingResult.of(" Something went wrong \n");
	}

}
