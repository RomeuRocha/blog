package br.edu.unirios.blog.servico;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.unirios.blog.entidades.Autor;
import br.edu.unirios.blog.repositorio.RepositorioAutor;
import br.edu.unirios.blog.servico.erros.ObjetoNaoEncontrado;


@Service
public class ServicoAutor {
	
	@Autowired
	private RepositorioAutor repo;
	
	public List<Autor> buscarTodos(){
		return repo.findAll();
	}
	
	public Autor buscar(int id) {
		Optional<Autor> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjetoNaoEncontrado(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Autor.class.getName()));	
	}
	
	public Autor salvar(Autor obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	public void deletar(int id) {
		buscar(id);//ou existe, ou irá gerar exception
		repo.deleteById(id);
	}
	
	public Autor editar(Autor obj) {
		Autor newObj = buscar(obj.getId());
		modificar(newObj, obj);
		return repo.save(newObj);
		
	}
	private void modificar(Autor newObj, Autor obj) {
		newObj.setDescricao(obj.getDescricao());
		newObj.setNome(obj.getNome());
	}
	
}
