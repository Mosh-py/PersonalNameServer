package com.moshood.web.controller;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.moshood.core.dto.MappedDto;
import com.moshood.core.model.Mapped;
import com.moshood.core.repository.MappedRepository;
import com.moshood.core.service.MappedService;

import jakarta.validation.UnexpectedTypeException;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/")
@Slf4j
public class MappedController {

	private MappedService mappedService;

	public MappedController(MappedService mappedService) {
		this.mappedService = mappedService;
	}

	@GetMapping
	public String getIndexPage(Model model) {
		
		List<Mapped> allMappedUrl = mappedService.getAllMApped();
		model.addAttribute("allUrl", allMappedUrl);
		return "index";
	}

	@PostMapping("/map")
	public String saveMappedUrl(@ModelAttribute MappedDto dto, Model model, RedirectAttributes redirectAttribute) {

		try {
			mappedService.saveMappedUrl(dto);
			model.addAttribute("goodStatus", " Saved Successfully");
			List<Mapped> allMappedUrl = mappedService.getAllMApped();
			model.addAttribute("allUrl", allMappedUrl);
		} catch (UnexpectedTypeException e) {
			model.addAttribute("status", "Url or alias already exist");

		} catch (DataIntegrityViolationException e) {
			model.addAttribute("status", "alias " + dto.getGivenName() + " already exists");
			log.info( " Encounterd a Data Integrity Exceoption");
		}
		
		redirectAttribute.addAttribute("goodStatus", "saved successfully");
		
		return getIndexPage(model);

	}
	
	
	@PostMapping("/search")
	public String searchUrl(@RequestParam String alias, Model model) {
		alias = alias.strip();
		try {
			Mapped mapped = mappedService.getMappedUrl(alias);
			String url = mapped.getURL();
			return "redirect:" + url;
		} catch (NoSuchElementException e) {
			log.warn("Encountered a NoSuchElementException for the alias " + alias);
			model.addAttribute("searchStatus", "alias " + alias + " isn't found");
			return getIndexPage(model);
		}

	}
	
	@PostMapping("/delete/{id}")
	public String getTheDeleteRequest(@PathVariable long id, Model model) {
		return deleteMapped(id, model);
	}
	
	public String deleteMapped( long id, Model model) {
		String alias = mappedService.getMappedUrlById(id).getGivenName();
		mappedService.deleteMappedUrl(id);
		model.addAttribute("goodStatus", "Deleted alias " + alias + " successfully");
		
		return getIndexPage(model);
	}

}
