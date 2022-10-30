package com.egg.news.controladores;

import com.egg.news.entidades.Noticia;
import com.egg.news.servicios.NoticiaServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/")
public class NoticiaControlador {

  @Autowired
  private NoticiaServicio noticiaServicio;

  @GetMapping("/")
  public String index(ModelMap modelo) {

    List<Noticia> noticias = noticiaServicio.listarNoticia();

    modelo.addAttribute("noticias", noticias);

    return "index.html";
  }

  @GetMapping("/{id}")
  public String noticia(@PathVariable String id, ModelMap modelo) {
    modelo.put("noticia", noticiaServicio.getOne(id));

    return "noticia.html";
  }

  @GetMapping("/guardar")
  public String formularioNoticia() {

    return "guardar_noticia.html";
  }

  @PostMapping("/guardar")
  public String guardarNoticia(
          @RequestParam String titulo,
          @RequestParam String cuerpo,
          @RequestParam MultipartFile foto,
          ModelMap modelo) {

    try {
      noticiaServicio.crearNoticia(titulo, cuerpo, foto);
      modelo.put("exito", "La noticia fue cargada correctamente");
    } catch (Exception ex) {
      modelo.put("error", ex.getMessage());
      return "guardar_noticia.html";
    }

    return "guardar_noticia.html";
  }

}
