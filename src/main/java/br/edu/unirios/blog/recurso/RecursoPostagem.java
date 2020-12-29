package br.edu.unirios.blog.recurso;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.edu.unirios.blog.entidades.Postagem;
import br.edu.unirios.blog.servico.ServicoPostagem;

@RestController
@RequestMapping(value="/postagem")
public class RecursoPostagem {
	
	@Autowired
	private ServicoPostagem servico;
	
	@RequestMapping( method = RequestMethod.GET)
	public ResponseEntity<Page<Postagem>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "textinput", defaultValue = "") String field) {
		Page<Postagem> list = servico.buscaPaginada(page, linesPerPage, orderBy, direction,field);

		return ResponseEntity.ok().body(list); 
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Postagem> salvar(@RequestBody Postagem obj) {
		obj = servico.salvar(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Postagem> buscar(@PathVariable Integer id) {
		Postagem obj = servico.buscar(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Postagem> editar(@RequestBody Postagem obj, @PathVariable Integer id) {
		obj.setId(id);
		obj = servico.editar(obj);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Postagem> deletar(@PathVariable Integer id) {
		Postagem obj = servico.deletar(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(value="/many/{ids}", method=RequestMethod.DELETE)
	public ResponseEntity<Integer[]> deletar(@PathVariable Integer[] ids) {
		
		for(Integer id: ids) {
			servico.deletar(id);
		}
		Integer[] vars = ids;
		return ResponseEntity.ok().body(vars);
	}
	
	@RequestMapping(value="/many/{ids}", method=RequestMethod.GET)
	public ResponseEntity<List<Postagem>> buscarMuitos(@PathVariable Integer[] ids) {
		List<Postagem> list = new ArrayList()<Postagem>();
		
		for(Integer id: ids) {
			Postagem obj = servico.buscar(id);
			list.add(obj);
		}
		
		return ResponseEntity.ok().body(list);
	}
	
}
