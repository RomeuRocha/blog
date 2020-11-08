package br.edu.unirios.blog.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor 
@EqualsAndHashCode(of = "id")
@Getter 
@Setter 

@Entity
public class Postagem implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String titulo;
	
	@Column(nullable=false, length=50000)
	private String texto;
	
	@ManyToOne
	private Autor autor;
	
	@OneToMany(mappedBy = "postagem")
	private List<Comentario> comentarios = new ArrayList<>();

	public Postagem(Integer id, String titulo, String texto, Autor autor) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.texto = texto;
		this.autor = autor;
	}
	
	

}
