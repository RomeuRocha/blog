package br.edu.unirios.blog.servico;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.edu.unirios.blog.entidades.Autor;
import br.edu.unirios.blog.repositorio.RepositorioAutor;
import br.edu.unirios.blog.servico.erros.ErroIntegridade;
import br.edu.unirios.blog.servico.erros.ObjetoNaoEncontrado;



@Service
public class ServicoAutor {
	
	@Autowired
	private RepositorioAutor repo;
	
	public List<Autor> buscarTodos(){
		return repo.findAll();
	}

	// + outros métodos
	
	public Autor buscar(int id) {
		Optional<Autor> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjetoNaoEncontrado(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Autor.class.getName()));	
	}
	
	public Autor salvar(Autor obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Autor update(Autor obj) {
		Autor newObj = buscar(obj.getId());
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
	
	private void updateData(Autor newObj, Autor obj) {
		newObj.setDescricao(obj.getDescricao());
		newObj.setNome(obj.getNome());
	}
	
}
