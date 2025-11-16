package com.moshood.core.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moshood.core.model.Mapped;

public interface MappedRepository extends JpaRepository<Mapped, Long>{

	Optional<Mapped> getByGivenName(String givenName);
	
	
}
