package br.edu.unirios.blog.repositorio;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.unirios.blog.entidades.Autor;

@Repository
public interface RepositorioAutor extends JpaRepository<Autor, Integer>{

    @Query("FROM Autor obj WHERE LOWER(obj.nome) like %:field%")
	public Page<Autor> buscarAutor(String field, Pageable  pageable );

}
