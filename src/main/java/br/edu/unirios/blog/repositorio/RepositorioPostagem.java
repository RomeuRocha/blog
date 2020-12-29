package br.edu.unirios.blog.repositorio;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.unirios.blog.entidades.Postagem;

@Repository
public interface RepositorioPostagem extends JpaRepository<Postagem, Integer>{

    @Query("FROM Postagem obj WHERE LOWER(obj.titulo) like %:field%")
	public Page<Postagem> buscarPostagem(String field, Pageable  pageable );

}
