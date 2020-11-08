package br.edu.unirios.blog.servico;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.unirios.blog.entidades.Comentario;
import br.edu.unirios.blog.repositorio.RepositorioComentario;
import br.edu.unirios.blog.servico.erros.ObjetoNaoEncontrado;


@Service
public class ServicoComentario {
	
	@Autowired
	private RepositorioComentario repo;
	
	public List<Comentario> buscarTodos(){
		return repo.findAll();
	}
	
	public Comentario buscar(int id) {
		Optional<Comentario> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjetoNaoEncontrado(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Comentario.class.getName()));	
	}
	
	public Comentario salvar(Comentario obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	public void deletar(int id) {
		buscar(id);//ou existe, ou irá gerar exception
		repo.deleteById(id);
	}
	
	public Comentario editar(Comentario obj) {
		Comentario newObj = buscar(obj.getId());
		modificar(newObj, obj);
		return repo.save(newObj);
		
	}
	private void modificar(Comentario newObj, Comentario obj) {
		newObj.setAutor(obj.getAutor());
		newObj.setPostagem(obj.getPostagem());
		newObj.setTexto(obj.getTexto());
	}
	
}
