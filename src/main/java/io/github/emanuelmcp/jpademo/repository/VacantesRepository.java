package io.github.emanuelmcp.jpademo.repository;

import io.github.emanuelmcp.jpademo.model.Vacante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacantesRepository extends JpaRepository<Vacante, Integer> {
}
