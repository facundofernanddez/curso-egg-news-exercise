package com.egg.news.controladores;

import com.egg.news.entidades.Noticia;
import com.egg.news.entidades.Usuario;
import com.egg.news.servicios.NoticiaServicio;
import com.egg.news.servicios.UsuarioServicio;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
  @Autowired
  private UsuarioServicio usuarioServicio;

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

  @GetMapping("/registrar")
  public String registroGet() {
    return "registro.html";
  }

  @PostMapping("/registro")
  public String registroPost(
          @RequestParam String nombreUsuario,
          @RequestParam String email,
          @RequestParam String password,
          @RequestParam String password2,
          ModelMap modelo) {

    try {

      usuarioServicio.registrar(nombreUsuario, email, password, password2);
      modelo.put("exito", "Fue registrado correctamente");

      return "registro.html";

    } catch (Exception ex) {

      modelo.put("error", ex.getMessage());
      modelo.put("nombreUsuario", nombreUsuario);
      modelo.put("email", email);

      return "registro.html";
    }
  }

  @GetMapping("/login")
  public String login(@RequestParam(required = false) String error, ModelMap modelo) {
    if (error != null) {
      modelo.put("error", "Usuario o contraseña inválidos!");
    }

    return "login.html";
  }

  @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
  @GetMapping("/inicio")
  public String inicio(HttpSession session) {

    Usuario logueado = (Usuario) session.getAttribute("usuariosession");

    if (logueado.getRol().toString().equals("ADMIN")) {
      return "redirect:/admin/dashboard";
    }

    return "inicio.html";
  }
}
