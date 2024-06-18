package com.aluracursos.literaturaAluraProject.repository;

import com.aluracursos.literaturaAluraProject.model.DatosAutor;
import com.aluracursos.literaturaAluraProject.model.DatosLibros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DatosAutorRepository extends JpaRepository<DatosAutor, Integer> {
    @Query("SELECT a FROM DatosAutor a WHERE a.fechaDeNacimiento <= :year AND (a.fechaDeFallecimiento >= :year OR a.fechaDeFallecimiento IS NULL)")
    List<DatosAutor> findAuthorsAliveInYear(@Param("year") String year);
}
