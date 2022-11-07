package io.github.emanuelmcp.jpademo;

import io.github.emanuelmcp.jpademo.model.Categoria;
import io.github.emanuelmcp.jpademo.repository.CategoriasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class JpaDemoApplication implements CommandLineRunner {

	@Autowired
	private CategoriasRepository repo;

	public static void main(String[] args) {
		SpringApplication.run(JpaDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		eliminarTodosJPA();
	}
	/*
	 * Metodo deleteAll() - Interfaz JPARepository
	 * */
	private void eliminarTodosJPA(){
		repo.deleteAllInBatch();
	}

	/*
	 * Metodo findAll() - Interfaz JPARepository
	 * */
	private void buscarTodasJPA(){
		List<Categoria> categorias = repo.findAll();
		for (Categoria cat : categorias) System.out.println(cat.getId() + " " + cat.getNombre());
	}

	/*
	 * Metodo saveAll() - Interfaz CrudRepository
	 * */
	private void guardarTodas(){
		List<Categoria> categorias = getListaCategorias();
		repo.saveAll(categorias);
	}

	/*
	 * Metodo existsById() - Interfaz CrudRepository
	 * */
	private void existeId(){
		boolean existe = repo.existsById(1);
		System.out.println(existe);
	}
	/*
	 * Metodo findAll() - Interfaz CrudRepository
	 * */
	private void buscarTodos(){
		Iterable<Categoria> categorias = repo.findAll();
		for (Categoria cat : categorias){
			System.out.println(cat);
		}
	}
	/*
	 * Metodo findAllById() - Interfaz CrudRepository
	 * */
	private void encontrarPorIds(){
		List<Integer> ids = new LinkedList<Integer>();
		ids.add(1);
		ids.add(4);
		ids.add(10);
		Iterable<Categoria> categorias = repo.findAllById(ids);
		for (Categoria cat : categorias) {
			System.out.println(cat);
		}
	}

	/*
	 * Metodo deleteAll() - Interfaz CrudRepository
	 * ESTE MÉTODO NO ES MUY EFICAZ YA QUE HACE n SENTENCIAS DELETE
	 * */
	private void eliminarTodos(){
		repo.deleteAll();
	}
	/*
	 * Metodo count() - Interfaz CrudRepository
	 * */
	private void conteo(){
		long count = repo.count();
		System.out.println("Total categorías: " + count);
	}

	/*
	 * Metodo deleteById - Interfaz CrudRepository
	 * */

	private void eliminar() {
		int idCategoria = 1;
		repo.deleteById(idCategoria);
	}

	/*
	 * Metodo save para actualizar registros - Interfaz CrudRepository
	 * */
	private void modificar(){
		Optional<Categoria> optional = repo.findById(1);
		if (optional.isPresent()) {
			Categoria catTmp = optional.get();
			catTmp.setNombre("Ingenieria de software");
			catTmp.setDescripcion("Desarrollo de sistemas");
			repo.save(catTmp);
		} else{
			System.out.println("Categoria no encontrada");
		}
	}
	/*
	* Metodo findById - Interfaz CrudRepository
	* */
	public void buscarPorId(){
		Optional<Categoria> optional = repo.findById(1);
		if (optional.isPresent()) System.out.println(optional.get());
		else System.out.println("Categoria no encontrada");
	}
	/*
	 * Metodo save - Interfaz CrudRepository
	 * */
	private void guardar(){
		Categoria cat = new Categoria();
		cat.setNombre("Finanzas");
		cat.setDescripcion("Trabajos relacionados con finanzas y contabilidad");
		repo.save(cat);
	}

	private List<Categoria> getListaCategorias(){
		List<Categoria> lista = new LinkedList<Categoria>();
		Categoria cat1 = new Categoria();
		cat1.setNombre("Programador de Blockchain");
		cat1.setDescripcion("Trabajos relacionados con Bitcoin y Criptomonedas");

		Categoria cat2 = new Categoria();
		cat2.setNombre("Soldador/Pintura");
		cat2.setDescripcion("Trabajos relacionados con soldadura, pintura y enderezado");

		Categoria cat3 = new Categoria();
		cat3.setNombre("Ingeniero Industrial");
		cat3.setDescripcion("Trabajos relacionados con Ingenieria industrial");

		lista.add(cat1);
		lista.add(cat2);
		lista.add(cat3);

		return lista;
	}
}
