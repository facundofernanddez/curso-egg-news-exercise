package com.egg.news.entidades;

import com.egg.news.enumeraciones.Rol;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Usuario {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  private String id;
  private String email;
  private String nombreUsuario;
  private String password;

//  @Temporal(TemporalType.DATE)
//  private Date fechaDeAlta;
  @Enumerated(EnumType.STRING)
  private Rol rol;

  private boolean activo;

  public Usuario() {
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getNombreUsuario() {
    return nombreUsuario;
  }

  public void setNombreUsuario(String nombreUsuario) {
    this.nombreUsuario = nombreUsuario;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

//  public Date getFechaDeAlta() {
//    return fechaDeAlta;
//  }
//
//  public void setFechaDeAlta(Date fechaDeAlta) {
//    this.fechaDeAlta = fechaDeAlta;
//  }
  public Rol getRol() {
    return rol;
  }

  public void setRol(Rol rol) {
    this.rol = rol;
  }

  public boolean isActivo() {
    return activo;
  }

  public void setActivo(boolean activo) {
    this.activo = activo;
  }

  @Override
  public String toString() {
    return "Usuario{" + "rol=" + rol + '}';
  }

}
