package br.edu.unirios.blog.servico;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort.Direction;

import br.edu.unirios.blog.entidades.Autor;
import br.edu.unirios.blog.repositorio.RepositorioAutor;
import br.edu.unirios.blog.servico.erros.ErroIntegridade;
import br.edu.unirios.blog.servico.erros.ObjetoNaoEncontrado;

@Service
public class ServicoAutor {

	@Autowired
	private RepositorioAutor repo;

	public List<Autor> buscarTodos() {
		return repo.findAll();
	}

	public Page<Autor> buscaPaginada(Integer page, Integer linesPerPage, String orderBy, String direction, String field) {
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.buscarAutor(field.toLowerCase(), pageRequest);

	}

	public Autor buscar(int id) {
		Optional<Autor> obj = repo.findById(id);
		return obj.orElseThrow(
				() -> new ObjetoNaoEncontrado("Objeto não encontrado! Id: " + id + ", Tipo: " + Autor.class.getName()));
	}

	public Autor salvar(Autor obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Autor deletar(int id) {
		Autor obj =  buscar(id);// ou existe, ou irá gerar exception
		try {
			repo.delete(obj);
			return obj;
		}
		catch (DataIntegrityViolationException e) {
			throw new ErroIntegridade("Não é possí­vel excluir : "+e.getMessage());
		}
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
