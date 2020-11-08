package br.edu.unirios.blog.recurso;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.edu.unirios.blog.entidades.Comentario;
import br.edu.unirios.blog.servico.ServicoComentario;

@RestController
@RequestMapping(value="/comentario")
public class RecursoComentario {
	
	@Autowired
	private ServicoComentario servico;
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> salvar(@RequestBody Comentario obj) {
		obj = servico.salvar(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Comentario>> buscarTodos() {
		List<Comentario> list = servico.buscarTodos();
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Comentario> buscar(@PathVariable Integer id) {
		Comentario obj = servico.buscar(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> editar(@RequestBody Comentario obj, @PathVariable Integer id) {
		obj.setId(id);
		obj = servico.editar(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deletar(@PathVariable Integer id) {
		servico.deletar(id);
		return ResponseEntity.noContent().build();
	}
	
}
