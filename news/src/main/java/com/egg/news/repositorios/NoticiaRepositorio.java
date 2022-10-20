package com.egg.news.repositorios;

import com.egg.news.entidades.Noticia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticiaRepositorio extends JpaRepository<Noticia, String>{
    
    @Query("SELECT a FROM Noticia a WHERE a.titulo = :titulo")
    public Noticia buscarNoticia(@Param("titulo") String titulo);
    
    @Query
    public Noticia guardarNoticia();
    
    @Query
    public Noticia actualizarNoticia();
    
    @Query
    public Noticia bajaNoticia();
    
}
