package br.edu.unirios.blog.entidades;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor 
@NoArgsConstructor 
@EqualsAndHashCode(of = "id")
@Getter 
@Setter 
@ToString(of = "texto")

@Entity
public class Comentario implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String texto;
	
	@ManyToOne
	private Autor autor;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "postagem_id")
	private Postagem postagem;
}
