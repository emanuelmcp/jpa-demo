package io.github.emanuelmcp.jpademo.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="Categorias") //debe coincidir con el nombre de la tabla
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Significa autoincrementable
    private Integer id;
    private String nombre;
    private String descripcion;
}
