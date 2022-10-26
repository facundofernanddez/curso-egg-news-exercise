package com.egg.news.servicios;

import com.egg.news.entidades.Imagen;
import com.egg.news.excepciones.MyException;
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
    public Imagen guardarImagen(MultipartFile archivo) throws MyException, Exception {

        validar(archivo);
        Imagen imagen = new Imagen();

        if (imagen != null) {
            try {
                imagen.setNombre(archivo.getName());
                imagen.setMime(archivo.getContentType());
                imagen.setPeso(archivo.getBytes());

                return imagenRepositorio.save(imagen);
            } catch (Exception e) {
                System.err.println(e.getMessage());
                return null;
            }
        }

        return imagen;
    }
    
    private void validar(MultipartFile archivo) throws MyException{
        if(archivo == null){
            throw new MyException("La imagen no puede estar vacia");
        }
    }
}
