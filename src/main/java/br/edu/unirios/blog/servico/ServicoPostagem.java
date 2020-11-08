package br.edu.unirios.blog.servico;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.unirios.blog.entidades.Postagem;
import br.edu.unirios.blog.repositorio.RepositorioPostagem;
import br.edu.unirios.blog.servico.erros.ObjetoNaoEncontrado;


@Service
public class ServicoPostagem {
	
	@Autowired
	private RepositorioPostagem repo;
	
	public List<Postagem> buscarTodos(){
		return repo.findAll();
	}
	
	public Postagem buscar(int id) {
		Optional<Postagem> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjetoNaoEncontrado(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Postagem.class.getName()));	
	}
	
	public Postagem salvar(Postagem obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	public void deletar(int id) {
		buscar(id);//ou existe, ou irá gerar exception
		repo.deleteById(id);
	}
	
	public Postagem editar(Postagem obj) {
		Postagem newObj = buscar(obj.getId());
		modificar(newObj, obj);
		return repo.save(newObj);
		
	}
	private void modificar(Postagem newObj, Postagem obj) {
		newObj.setAutor(obj.getAutor());
		newObj.setComentarios(obj.getComentarios());
		newObj.setTexto(obj.getTexto());
		newObj.setTitulo(obj.getTitulo());
	}
	
}
