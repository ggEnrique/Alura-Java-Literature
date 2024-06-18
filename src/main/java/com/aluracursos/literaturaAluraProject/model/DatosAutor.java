package com.aluracursos.literaturaAluraProject.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;


@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "datos_autor")
public class DatosAutor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonAlias("name")
    private String nombre;

    @JsonAlias("birth_year")
    private String fechaDeNacimiento;

    @JsonAlias("death_year")
    private String fechaDeFallecimiento;

    @ManyToOne
    @JoinColumn(name = "libro_id")
    private DatosLibros libro;

    // Constructors, getters, and setters
    public DatosAutor() {}

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(String fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public String getFechaDeFallecimiento() {
        return fechaDeFallecimiento;
    }

    public void setFechaDeFallecimiento(String fechaDeFallecimiento) {
        this.fechaDeFallecimiento = fechaDeFallecimiento;
    }

    public DatosLibros getLibro(){
        return libro;
    }

    public void setLibro(DatosLibros libro){
        this.libro = libro;
    }

    @Override
    public String toString() {
        return "DatosAutor{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", fechaDeNacimiento='" + fechaDeNacimiento + '\'' +
                ", fechaDeFallecimiento='" + fechaDeFallecimiento + '\'' +
                ", libro=" + libro +
                '}';
    }
}