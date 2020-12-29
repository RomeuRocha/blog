package br.edu.unirios.blog.servico;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort.Direction;

import br.edu.unirios.blog.entidades.Postagem;
import br.edu.unirios.blog.repositorio.RepositorioPostagem;
import br.edu.unirios.blog.servico.erros.ErroIntegridade;
import br.edu.unirios.blog.servico.erros.ObjetoNaoEncontrado;


@Service
public class ServicoPostagem {
	
	@Autowired
	private RepositorioPostagem repo;
	
	public List<Postagem> buscarTodos() {
		return repo.findAll();
	}

	public Page<Postagem> buscaPaginada(Integer page, Integer linesPerPage, String orderBy, String direction, String field) {
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.buscarPostagem(field.toLowerCase(), pageRequest);

	}

	public Postagem buscar(int id) {
		Optional<Postagem> obj = repo.findById(id);
		return obj.orElseThrow(
				() -> new ObjetoNaoEncontrado("Objeto não encontrado! Id: " + id + ", Tipo: " + Postagem.class.getName()));
	}

	public Postagem salvar(Postagem obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Postagem deletar(int id) {
		Postagem obj =  buscar(id);// ou existe, ou irá gerar exception
		try {
			repo.delete(obj);
			return obj;
		}
		catch (DataIntegrityViolationException e) {
			throw new ErroIntegridade("Não é possí­vel excluir : "+e.getMessage());
		}
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
