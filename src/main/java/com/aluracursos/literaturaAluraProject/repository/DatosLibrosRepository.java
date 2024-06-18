package com.aluracursos.literaturaAluraProject.repository;

import com.aluracursos.literaturaAluraProject.model.DatosLibros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DatosLibrosRepository extends JpaRepository<DatosLibros, Long> {
    List<DatosLibros> findAllByAutorNombre(String nombre);

    @Query("SELECT l FROM DatosLibros l JOIN l.idiomas i WHERE i = ?1")
    List<DatosLibros> findByIdiomasContains(String idioma);

    Optional<DatosLibros> findByTitulo(String titulo);


}
