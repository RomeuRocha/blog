package br.edu.unirios.blog.servico;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.edu.unirios.blog.entidades.Postagem;
import br.edu.unirios.blog.repositorio.RepositorioPostagem;
import br.edu.unirios.blog.servico.erros.ErroIntegridade;
import br.edu.unirios.blog.servico.erros.ObjetoNaoEncontrado;


@Service
public class ServicoPostagem {
	
	@Autowired
	private RepositorioPostagem repo;
	
	public List<Postagem> buscarTodos(){
		return repo.findAll();
	}

	// + outros métodos
	
	public Postagem buscar(int id) {
		Optional<Postagem> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjetoNaoEncontrado(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Postagem.class.getName()));	
	}
	
	public Postagem salvar(Postagem obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Postagem update(Postagem obj) {
		Postagem newObj = buscar(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
		
	}
	
	public void delete(int id) {
		buscar(id);//ou existe, ou irá gerar exception
		try {
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new ErroIntegridade("Sem permissão para excluir um autor que possui postagens ou comentários");
		}
	}
	
	private void updateData(Postagem newObj, Postagem obj) {
		newObj.setAutor(obj.getAutor());
		newObj.setComentarios(obj.getComentarios());
		newObj.setTexto(obj.getTexto());
		newObj.setTitulo(obj.getTitulo());
	}
	
}
