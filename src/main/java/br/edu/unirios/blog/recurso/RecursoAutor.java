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

import br.edu.unirios.blog.entidades.Autor;
import br.edu.unirios.blog.servico.ServicoAutor;

@RestController
@RequestMapping(value="/autor")
public class RecursoAutor {
	
	@Autowired
	private ServicoAutor servico;
	
	@RequestMapping( method = RequestMethod.GET)
	public ResponseEntity<Page<Autor>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "textinput", defaultValue = "") String field) {
		Page<Autor> list = servico.buscaPaginada(page, linesPerPage, orderBy, direction,field);

		return ResponseEntity.ok().body(list); 
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Autor> salvar(@RequestBody Autor obj) {
		obj = servico.salvar(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Autor> buscar(@PathVariable Integer id) {
		Autor obj = servico.buscar(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Autor> editar(@RequestBody Autor obj, @PathVariable Integer id) {
		obj.setId(id);
		obj = servico.editar(obj);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Autor> deletar(@PathVariable Integer id) {
		Autor obj = servico.deletar(id);
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
	public ResponseEntity<List<Autor>> buscarMuitos(@PathVariable Integer[] ids) {
		List<Autor> list = new ArrayList<Autor>();
		
		for(Integer id: ids) {
			Autor obj = servico.buscar(id);
			list.add(obj);
		}
		
		return ResponseEntity.ok().body(list);
	}
	
}
