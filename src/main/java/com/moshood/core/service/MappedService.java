package com.moshood.core.service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.moshood.core.dto.MappedDto;
import com.moshood.core.model.Mapped;
import com.moshood.core.repository.MappedRepository;

import jakarta.validation.UnexpectedTypeException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MappedService {
	private MappedRepository mappedRepository;

	private Mapped mapped;

	public MappedService(MappedRepository mappedRepository, Mapped mapped) {
		this.mappedRepository = mappedRepository;
		this.mapped = mapped;
	}

	public void saveMappedUrl(MappedDto dto) throws DataIntegrityViolationException {
		Mapped mapped = new Mapped();
		log.info(dto.getGivenName());
		mapped.setGivenName(dto.getGivenName());
		mapped.setURL(dto.getUrl());
		try {
			mappedRepository.save(mapped);
		} catch (UnexpectedTypeException e) {
			log.info(" Error , unable to print save to the repository ");
			e.printStackTrace();
			throw new UnexpectedTypeException();
		}
	}

	public Mapped getMappedUrl(String givenName) {

		try {
			Optional<Mapped> mappedUrl = mappedRepository.getByGivenName(givenName);
			return mappedUrl.get();
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException();
		}
	}
	
	public Mapped getMappedUrlById(long id) {
		Mapped mappedUrl = mappedRepository.getReferenceById(id);
		return mappedUrl;
	}

	public List<Mapped> getAllMApped() {
		return mappedRepository.findAll();
	}

	public void deleteMappedUrl(long id) {
		Mapped mappedUrl = getMappedUrlById(id);
		mappedRepository.delete(mappedUrl);
	}
}
