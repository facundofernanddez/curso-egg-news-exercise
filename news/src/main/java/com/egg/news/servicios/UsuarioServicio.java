package com.egg.news.servicios;

import com.egg.news.entidades.Usuario;
import com.egg.news.enumeraciones.Rol;
import com.egg.news.excepciones.MyException;
import com.egg.news.repositorios.UsuarioRepositorio;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class UsuarioServicio implements UserDetailsService {

  @Autowired
  private UsuarioRepositorio usuarioRepositorio;

  @Transactional
  public void registrar(String nombreUsuario, String email, String password, String password2) throws MyException, Exception {

    validar(nombreUsuario, email, password, password2);

    Usuario usuario = new Usuario();

    usuario.setNombreUsuario(nombreUsuario);
    usuario.setEmail(email);
    usuario.setPassword(new BCryptPasswordEncoder().encode(password));

    usuario.setRol(Rol.USER);

    usuarioRepositorio.save(usuario);
  }

  private void validar(String nombreUsuario, String email, String password, String password2) throws MyException {

    if (nombreUsuario.isEmpty() || nombreUsuario == null) {
      throw new MyException("El nombre no puede estar vacio");
    }

    if (email.isEmpty() || email == null) {
      throw new MyException("El email no puede estar vacio");
    }

    if (password.isEmpty() || password == null || password.length() <= 5) {
      throw new MyException("La contraseña no puede estar vacio y debe tener mas de 5 caracteres");
    }

    if (!password.equals(password2)) {
      throw new MyException("Las contraseñas ingresadas deben ser iguales");
    }
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

    Usuario usuario = usuarioRepositorio.buscarPorEmail(email);

    if (usuario != null) {

      List<GrantedAuthority> permisos = new ArrayList();

      GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + usuario.getRol().toString());

      permisos.add(p);

      ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

      HttpSession sesion = attr.getRequest().getSession(true);

      sesion.setAttribute("usuariosession", usuario);

      return new User(usuario.getEmail(), usuario.getPassword(), permisos);
    } else {
      return null;
    }
  }

}
