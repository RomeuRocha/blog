package br.edu.unirios.blog.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.edu.unirios.blog.entidades.Autor;
import br.edu.unirios.blog.entidades.Comentario;
import br.edu.unirios.blog.entidades.Postagem;
import br.edu.unirios.blog.repositorio.RepositorioAutor;
import br.edu.unirios.blog.repositorio.RepositorioComentario;
import br.edu.unirios.blog.repositorio.RepositorioPostagem;

@Configuration
public class Config implements CommandLineRunner{
	
	@Autowired
	private RepositorioAutor repositorioAutor;
	
	@Autowired
	private RepositorioComentario repositorioComentario;
	
	@Autowired
	private RepositorioPostagem repositorioPostagem;
	
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Autor autor1 = new Autor(null, "j.k rowling", "escritora, roteirista e produtora cinematográfica britânica, notória por escrever a série de livros Harry Potter");
		Autor autor2 = new Autor(null, "Masashi Kishimoto", " mangaká e escritor japonês, entre seus maiores trabalhos está Naruto criado em 1997");
		
		repositorioAutor.saveAll(Arrays.asList(autor1,autor2));
		
		Postagem post1 = new Postagem(null, "Relacionando o contexo politico da ficção com a realidade", "Harry Potter é um garoto órfão, criado pelos tios que tratam ele mal por ser filho de bruxos.\r\n" + 
				"Ainda mais, dormia em baixo da escada, tinha que usar as roupas velhas que o primo não queria mais e comia mal, enquanto a família se alimentava do bom e do melhor.\r\n" + 
				"Ele foi deixado na porta da casa dos tios ainda bebê, por Dumbledore, Minerva e Hagrid, após a morte de seus pais. No entanto, no seu aniversário de 11 anos, Potter recebe uma carta que irá mudar sua vida totalmente.\r\n" + 
				"No primeiro dia de aula em Hogwarts, o chapéu seletor escolhe Harry para a Grifinoria, uma das quatro casas, sendo as outras Corvinal, Lufa-Lufa e Sonserina, cada uma com suas características.\r\n" + 
				"Imediatamente na escola, nasce uma grande amizade com Rony, que conheceu na estação de têm a caminho da escola e Hermione, onde vão viver grandes aventuras, sendo uma delas, a trama principal da história, que é desvendar o mistério sobre a pedra filosofal, (substância produzida pelo alquimista Nicolau Flamel, que tem poder da imortalidade e transforma qualquer metal em ouro). Os garotos desconfiam que Snape esteja tentando rouba-la.", autor1);
		
		Postagem post2 = new Postagem(null, "Kakashi sobre naruto", "O Naruto pode ser um pouco duro às vezes\r\n" + 
				"Talvez você não saiba disso\r\n" + 
				"Mas o Naruto também\r\n" + 
				"Cresceu sem pai\r\n" + 
				"\r\n" + 
				"Na verdade, ele nunca conheceu nenhum de seus pais\r\n" + 
				"E nunca teve nenhum amigo em nossa aldeia\r\n" + 
				"Mesmo assim, eu nunca vi ele chorar\r\n" + 
				"Ficar zangado ou se dar por vencido\r\n" + 
				"\r\n" + 
				"Ele está sempre disposto a melhorar\r\n" + 
				"Ele quer ser respeitado, é o sonho dele\r\n" + 
				"E o Naruto daria a vida por isso sem hesitar\r\n" + 
				"Meu palpite é que ele se cansou de chorar\r\n" + 
				"E decidiu fazer alguma coisa a respeito", autor2);
		repositorioPostagem.saveAll(Arrays.asList(post1,post2));
		
		Comentario coment1 = new Comentario(null, "Muito Legal", autor1, post2);
		Comentario coment2 = new Comentario(null, "ótimo trabalho", autor2, post1);
		
		repositorioComentario.saveAll(Arrays.asList(coment1,coment2));
		
	}

}
