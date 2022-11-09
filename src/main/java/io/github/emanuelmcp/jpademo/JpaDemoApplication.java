package io.github.emanuelmcp.jpademo;

import io.github.emanuelmcp.jpademo.methods.CategoriaMetodos;
import io.github.emanuelmcp.jpademo.model.Categoria;
import io.github.emanuelmcp.jpademo.model.Vacante;
import io.github.emanuelmcp.jpademo.repository.CategoriasRepository;
import io.github.emanuelmcp.jpademo.repository.VacantesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class JpaDemoApplication implements CommandLineRunner {
	@Autowired
	VacantesRepository repoVacantes;
	@Autowired
	CategoriaMetodos categoriaMetodos;

	public static void main(String[] args) {
		SpringApplication.run(JpaDemoApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		categoriaMetodos.buscarTodosPaginacionOrdenados();
	}

	public void buscarVacantes(){
		List<Vacante> lista = repoVacantes.findAll();
		for (Vacante v : lista) System.out.println(v.getId() + " " + v.getNombre());
	}

}
