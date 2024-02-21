package com.example.trabajoapi.repositorio;

import java.util.List;

public class Ave {
    private int id;
    private String nombre;
    private String imagen;
    private List<Habitat> id_habitat;
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

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public List<Habitat> getHabitat() {
        return id_habitat;
    }

    public void setHabitat(List<Habitat> habitat) {
        this.id_habitat = habitat;
    }
}

