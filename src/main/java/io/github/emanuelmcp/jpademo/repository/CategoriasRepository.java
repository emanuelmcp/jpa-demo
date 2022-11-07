package io.github.emanuelmcp.jpademo.repository;

import io.github.emanuelmcp.jpademo.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriasRepository extends JpaRepository<Categoria, Integer> {
}
