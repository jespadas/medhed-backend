package com.jespadas.medhedBackendApi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jespadas.medhedBackendApi.entities.Shift;

@Repository
public interface ShiftRepository extends JpaRepository<Shift, Long> {

	Optional<Shift> findById(Long id);

}
