package com.egg.news.servicios;

import com.egg.news.entidades.Imagen;
import com.egg.news.entidades.Noticia;
import com.egg.news.excepciones.MyException;
import com.egg.news.repositorios.NoticiaRepositorio;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class NoticiaServicio {

  @Autowired
  private NoticiaRepositorio noticiaRepositorio;

  @Autowired
  private ImagenServicio imagenServicio;

  @Transactional
  public void crearNoticia(String titulo, String cuerpo, MultipartFile foto) throws MyException, Exception {

    validar(titulo, cuerpo);

    Noticia noticia = new Noticia();

    noticia.setTitulo(titulo);
    noticia.setCuerpo(cuerpo);

    Imagen imagen = imagenServicio.guardarImagen(foto);
    noticia.setFoto(imagen);

    noticiaRepositorio.save(noticia);
  }

  public List listarNoticia() {
    List<Noticia> noticias = noticiaRepositorio.findAll();

    return noticias;
  }

  public Noticia getOne(String id) {
    return noticiaRepositorio.getOne(id);
  }

  public Noticia buscarNoticiaPorId(String id) {
    Optional<Noticia> respuesta = noticiaRepositorio.findById(id);

    Noticia noticia;

    if (respuesta.isPresent()) {
      noticia = respuesta.get();
    } else {
      noticia = null;
    }

    return noticia;
  }

//    @Transactional
//    public void actualizarNoticia(String id) throws MyException {
//
//        String titulo = leer.next();
//        String cuerpo = leer.next();
//
//        validar(titulo, cuerpo);
//
//        Optional<Noticia> respuesta = noticiaRepositorio.findById(id);
//
//        if (respuesta.isPresent()) {
//            Noticia noticia = respuesta.get();
//
//            noticia.setTitulo(titulo);
//            noticia.setCuerpo(cuerpo);
////            noticia.setFoto(foto);
//
//            noticiaRepositorio.save(noticia);
//        }
//    }
  @Transactional
  public void bajaNoticia(String id) {

    Optional<Noticia> respuesta = noticiaRepositorio.findById(id);

    if (respuesta.isPresent()) {
      Noticia noticia = respuesta.get();

      noticia.setCuerpo(null);
      noticia.setTitulo(null);
      noticia.setId(null);

      noticiaRepositorio.save(noticia);
    }
  }

  private void validar(String titulo, String cuerpo) throws MyException {
    if (titulo.isEmpty() || titulo == null) {
      throw new MyException("El titulo no puede estar vacio ni ser nulo");
    }

    if (cuerpo.isEmpty() || cuerpo == null) {
      throw new MyException("El cuerpo de la noticia no puede estar vacio ni ser nulo");
    }

  }

}
