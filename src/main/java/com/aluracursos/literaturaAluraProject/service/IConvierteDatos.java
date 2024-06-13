package com.aluracursos.literaturaAluraProject.service;

public interface IConvierteDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
}
