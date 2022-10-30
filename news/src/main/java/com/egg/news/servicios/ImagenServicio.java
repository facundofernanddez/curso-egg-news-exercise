package com.egg.news.servicios;

import com.egg.news.entidades.Imagen;
import com.egg.news.repositorios.ImagenRepositorio;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImagenServicio {

  @Autowired
  private ImagenRepositorio imagenRepositorio;

  @Transactional
  public Imagen guardarImagen(MultipartFile foto) throws Exception {

    Imagen imagen = new Imagen();

    try {
      imagen.setNombre(foto.getName());
      imagen.setMime(foto.getContentType());
      imagen.setPeso(foto.getBytes());

      return imagenRepositorio.save(imagen);

    } catch (Exception e) {
      System.err.println(e.getMessage());
    }

    return imagen;
  }
}
