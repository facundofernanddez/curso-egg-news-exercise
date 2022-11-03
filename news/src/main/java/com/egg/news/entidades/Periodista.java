package com.egg.news.entidades;

import java.util.ArrayList;
import javax.persistence.Entity;

@Entity
public class Periodista extends Usuario {

  private ArrayList<Noticia> misNoticias;
  private Integer sueldoMensual;

}
