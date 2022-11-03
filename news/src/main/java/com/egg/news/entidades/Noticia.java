package com.egg.news.entidades;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Noticia {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  private String id;
  private String titulo;
  private String cuerpo;
  @OneToOne
  private Imagen foto;
  @OneToOne
  private Periodista creador;

  public Noticia() {
  }

  public Noticia(String id, String titulo, String cuerpo, Imagen foto, Periodista creador) {
    this.id = id;
    this.titulo = titulo;
    this.cuerpo = cuerpo;
    this.foto = foto;
    this.creador = creador;
  }

  public Periodista getCreador() {
    return creador;
  }

  public void setCreador(Periodista creador) {
    this.creador = creador;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public String getCuerpo() {
    return cuerpo;
  }

  public void setCuerpo(String cuerpo) {
    this.cuerpo = cuerpo;
  }

  public Imagen getFoto() {
    return foto;
  }

  public void setFoto(Imagen foto) {
    this.foto = foto;
  }
}
