
package com.egg.news.entidades;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Imagen {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String mime;
    private String nombre;
    
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] peso;

    public Imagen() {
    }

    public Imagen(String id, String mime, String nombre, byte[] peso) {
        this.id = id;
        this.mime = mime;
        this.nombre = nombre;
        this.peso = peso;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public byte[] getPeso() {
        return peso;
    }

    public void setPeso(byte[] peso) {
        this.peso = peso;
    }
}
