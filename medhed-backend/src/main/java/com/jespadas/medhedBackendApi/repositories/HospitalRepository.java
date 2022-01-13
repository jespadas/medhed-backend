package com.jespadas.medhedBackendApi.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jespadas.medhedBackendApi.entities.Hospital;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long> {

	Optional<Hospital> findById(Long id);

	Optional<Hospital> findByName(String nameHospital);

	@Query("SELECT HOSP FROM Hospital HOSP")
	public List<Hospital> findHospitals();

}
