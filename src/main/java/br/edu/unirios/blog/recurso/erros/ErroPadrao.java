package br.edu.unirios.blog.recurso.erros;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor

public class ErroPadrao implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long momento;
	private Integer status;
	private String erro;
	private String mensagem;
	private String caminho;
	
	

}
