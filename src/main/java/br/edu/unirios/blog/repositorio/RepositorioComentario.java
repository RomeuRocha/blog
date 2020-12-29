package br.edu.unirios.blog.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.edu.unirios.blog.entidades.Comentario;

@Repository
public interface RepositorioComentario extends JpaRepository<Comentario, Integer>{

}
