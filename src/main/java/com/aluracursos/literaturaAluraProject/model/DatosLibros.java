package com.aluracursos.literaturaAluraProject.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "datos_libros")
public class DatosLibros {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        @JsonAlias("title")
        private String titulo;

        @JsonAlias("download_count")
        private Double numeroDeDescargas;

        @JsonAlias("authors")
        @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
        @JoinColumn(name = "libro_id")
        private List<DatosAutor> autor;

        @JsonAlias("languages")
        @ElementCollection(fetch = FetchType.EAGER)
        @CollectionTable(name = "libro_idiomas", joinColumns = @JoinColumn(name = "libro_id"))
        @Column(name = "idioma")
        private List<String> idiomas;

        // Constructors, getters, and setters
        public DatosLibros() {}

        // Getters and Setters
        public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
        }

        public String getTitulo() {
                return titulo;
        }

        public void setTitulo(String titulo) {
                this.titulo = titulo;
        }

        public Double getNumeroDeDescargas() {
                return numeroDeDescargas;
        }

        public void setNumeroDeDescargas(Double numeroDeDescargas) {
                this.numeroDeDescargas = numeroDeDescargas;
        }

        public List<DatosAutor> getAutor() {
                return autor;
        }

        public void setAutor(List<DatosAutor> autor) {
                this.autor = autor;
        }

        public List<String> getIdiomas() {
                return idiomas;
        }

        public void setIdiomas(List<String> idiomas) {
                this.idiomas = idiomas;
        }

        @Override
        public String toString() {
                return "DatosLibros{" +
                        "id=" + id +
                        ", titulo='" + titulo + '\'' +
                        ", numeroDeDescargas=" + numeroDeDescargas +
                        ", autor=" + autor +
                        ", idiomas=" + idiomas +
                        '}';
        }
}