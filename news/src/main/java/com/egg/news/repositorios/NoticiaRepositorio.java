package com.egg.news.repositorios;

import com.egg.news.entidades.Noticia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticiaRepositorio extends JpaRepository<Noticia, String>{
    
    @Query("SELECT a FROM Noticia a WHERE a.id = :id")
    public Noticia buscarNoticiaPorId(@Param("id") String id);
    
    @Query("SELECT a FROM Noticia a WHERE a.id = :id")
    public Noticia actualizarNoticia(@Param("id") String id);
    
    @Query("SELECT a FROM Noticia a WHERE a.id = :id")
    public Noticia bajaNoticia(@Param("id") String id);
    
}
