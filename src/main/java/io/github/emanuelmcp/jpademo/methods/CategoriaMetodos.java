package io.github.emanuelmcp.jpademo.methods;

import io.github.emanuelmcp.jpademo.model.Categoria;
import io.github.emanuelmcp.jpademo.repository.CategoriasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaMetodos {
    @Autowired
    public CategoriasRepository repoCategorias;
    /*
     * Metodo findAll() paginado ordenado por atributo - Interfaz JPARepository
     * */

    public void buscarTodosPaginacionOrdenados(){
        Page<Categoria> page = repoCategorias.findAll(PageRequest.of(0, 5, Sort.by("nombre")));
        System.out.println("Total registros: " +page.getTotalElements());
        System.out.println("Total paginas: " + page.getTotalPages());
        for (Categoria categoria: page.getContent()) System.out.println(categoria.getNombre());
    }

    /*
     * Metodo findAll() paginado - Interfaz JPARepository
     * */

    public  void buscarTodosPaginacion(){
        Page<Categoria> page = repoCategorias.findAll(PageRequest.of(0, 5));
        System.out.println("Total registros: " +page.getTotalElements());
        System.out.println("Total paginas: " + page.getTotalPages());
        for (Categoria categoria: page.getContent()) System.out.println(categoria.getNombre());
    }
    /*
     * Metodo findAll() ordenado - Interfaz JPARepository
     * */
    public void buscarTodosOrdenados(){
        List<Categoria> categorias = repoCategorias.findAll(Sort.by("nombre").descending()); //DEBE COINCIDIR CON EL NOMBRE DEL ATRIBUTO DE LA CLASE
        for (Categoria cat : categorias) System.out.println(cat.getId() + " " + cat.getNombre());
    }

    /*
     * Metodo deleteAll() - Interfaz JPARepository
     * */
    public void eliminarTodosJPA(){
        repoCategorias.deleteAllInBatch();
    }

    /*
     * Metodo findAll() - Interfaz JPARepository
     * */
    public void buscarTodasJPA(){
        List<Categoria> categorias = repoCategorias.findAll();
        for (Categoria cat : categorias) System.out.println(cat.getId() + " " + cat.getNombre());
    }

    /*
     * Metodo saveAll() - Interfaz CrudRepository
     * */
    public void guardarTodas(){
        List<Categoria> categorias = getListaCategorias();
        repoCategorias.saveAll(categorias);
    }

    /*
     * Metodo existsById() - Interfaz CrudRepository
     * */
    public void existeId(){
        boolean existe = repoCategorias.existsById(1);
        System.out.println(existe);
    }
    /*
     * Metodo findAll() - Interfaz CrudRepository
     * */
    public void buscarTodos(){
        Iterable<Categoria> categorias = repoCategorias.findAll();
        for (Categoria cat : categorias){
            System.out.println(cat);
        }
    }
    /*
     * Metodo findAllById() - Interfaz CrudRepository
     * */
    public void encontrarPorIds(){
        List<Integer> ids = new LinkedList<Integer>();
        ids.add(1);
        ids.add(4);
        ids.add(10);
        Iterable<Categoria> categorias = repoCategorias.findAllById(ids);
        for (Categoria cat : categorias) {
            System.out.println(cat);
        }
    }

    /*
     * Metodo deleteAll() - Interfaz CrudRepository
     * ESTE MÉTODO NO ES MUY EFICAZ YA QUE HACE n SENTENCIAS DELETE
     * */
    public void eliminarTodos(){
        repoCategorias.deleteAll();
    }
    /*
     * Metodo count() - Interfaz CrudRepository
     * */
    public void conteo(){
        long count = repoCategorias.count();
        System.out.println("Total categorías: " + count);
    }

    /*
     * Metodo deleteById - Interfaz CrudRepository
     * */

    public void eliminar() {
        int idCategoria = 1;
        repoCategorias.deleteById(idCategoria);
    }

    /*
     * Metodo save para actualizar registros - Interfaz CrudRepository
     * */
    public void modificar(){
        Optional<Categoria> optional = repoCategorias.findById(1);
        if (optional.isPresent()) {
            Categoria catTmp = optional.get();
            catTmp.setNombre("Ingenieria de software");
            catTmp.setDescripcion("Desarrollo de sistemas");
            repoCategorias.save(catTmp);
        } else{
            System.out.println("Categoria no encontrada");
        }
    }
    /*
     * Metodo findById - Interfaz CrudRepository
     * */
    public void buscarPorId(){
        Optional<Categoria> optional = repoCategorias.findById(1);
        if (optional.isPresent()) System.out.println(optional.get());
        else System.out.println("Categoria no encontrada");
    }
    /*
     * Metodo save - Interfaz CrudRepository
     * */
    public void guardar(){
        Categoria cat = new Categoria();
        cat.setNombre("Finanzas");
        cat.setDescripcion("Trabajos relacionados con finanzas y contabilidad");
        repoCategorias.save(cat);
    }

    public List<Categoria> getListaCategorias(){
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
