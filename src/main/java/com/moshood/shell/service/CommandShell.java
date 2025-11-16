package com.moshood.shell.service;

import java.io.IOException;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.Option;

import com.moshood.core.dto.MappedDto;
import com.moshood.core.model.Mapped;
import com.moshood.core.service.MappedService;
import com.moshood.shell.exceptions.CustomExceptionResolver;



@Command
public class CommandShell {

	private MappedService mappedService;
	private CustomExceptionResolver customExceptionResolver;
	
	public CommandShell(MappedService mappedService) {
		this.mappedService = mappedService;
	}

	@Command(alias = "saveUrl")
	public String saveUrl(@Option(required = true, longNames = "URL", shortNames = 'u') String url,
			@Option(required = true, longNames = "alias") String alias) {

		MappedDto dto = new MappedDto();
		dto.setGivenName(alias);
		dto.setUrl(url);

		mappedService.saveMappedUrl(dto);

		return " Saved Successfull";
	}
	
	@Command(alias = {"getUrl", "geturl"})
	public String getUrl(@Option(required =true, description = " get the url" , longNames = "alias") String alias ) throws IOException {
		
		Mapped mapped = mappedService.getMappedUrl(alias);
		ProcessBuilder processBuilder = new ProcessBuilder();
		processBuilder.command("xdg-open", mapped.getURL());
		processBuilder.start();
		return "opening browser";
	}
	
	@Command(alias = {"getAllUrl", "getAllUrl"})
	public void getAllUrl() {
		
		List<Mapped> maps = mappedService.getAllMApped();
		maps.forEach((mapped)->{
			System.out.println("The alias is " + mapped.getGivenName()+  ", and url is " + mapped.getURL());
		});
		
	}
}
