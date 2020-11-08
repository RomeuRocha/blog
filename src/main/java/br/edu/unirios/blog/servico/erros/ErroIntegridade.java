package br.edu.unirios.blog.servico.erros;

public class ErroIntegridade extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ErroIntegridade(String msg) {
		super(msg);
	}

	public ErroIntegridade(String msg, Throwable cause) {
		super(msg, cause);
	}

}
