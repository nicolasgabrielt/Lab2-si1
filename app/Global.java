

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;











import models.Anunciante;
import models.Anuncio;
import models.Contato;
import models.Instrumentos;
import models.Style;
import play.*;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import models.dao.GenericDAO;

public class Global extends GlobalSettings {
	
	private static final GenericDAO dao = new GenericDAO();;
	@SuppressWarnings("resource")
	@Transactional
	public void onStart(Application app) {
	
		// Colocar estilos no bd
		
		JPA.withTransaction(new play.libs.F.Callback0() {
			@Override
			public void invoke() throws Throwable {
					Scanner in;
					
					 in = new Scanner(new FileInputStream(new File("app/styles").getCanonicalPath())
								, "UTF-8");
					
					
				
					
					while(in.hasNextLine()){
						String nomeEstilo =in.nextLine();
						
						dao.persist(new Style(nomeEstilo));
					}
				
					dao.flush();
					
					 in = new Scanner(new FileInputStream(new File("app/instrumentos").getCanonicalPath())
						, "UTF-8");
					
					
					while(in.hasNextLine()){
						String nomeInstrumento =in.nextLine();
						dao.persist(new Instrumentos(nomeInstrumento));
					}
				
					dao.flush();			
			
					if (dao.findAllByClass(Anuncio.class).size() == 0){
					
						for (int i = 0; i < 500; i++) {
						Anuncio anuncio = new Anuncio();
						Anunciante anunciante = new Anunciante();
						Contato contatos = new Contato();
						List<Instrumentos> instrumentos = new ArrayList<>();
						List<Style> gosta = new ArrayList<>();
						List<Style> naoGosta = new ArrayList<>();

						if (i % 2 == 0) {
							anuncio.setNome("Quero uma banda");
							anuncio.setDescricao("Queremos banda massa, que toque muito, tenha disponibilidade para sair, palavra chave '123' ");
							anuncio.setPalavraChave("123");
							anunciante.setNome("Nicolas");
							anunciante.setCidade("Campina Grande");
							anunciante.setBairro("Tambor");
							instrumentos.add(new Instrumentos("Piano"));
							instrumentos.add(new Instrumentos("Bongó"));
							anunciante.setListInstrumentos(instrumentos);

							anuncio.setInteresse("Em Banda");
							
							gosta.add(new Style("Bossa Nova"));
							gosta.add(new Style("Forró"));
							gosta.add(new Style("MPB"));
							anuncio.setStylesLike(gosta);

							naoGosta.add(new Style("Funk"));
							naoGosta.add(new Style("Funk Carioca"));
							anuncio.setStylesNotLike(naoGosta);

							contatos.setEmail("niolas.13@hotmail.com");
							contatos.setOutrosContatos("http://www.facebook.com.br/");
							contatos.setTelefone("(83)9916-5951");
							dao.persist(contatos);
							dao.flush();
							anunciante.setContato(contatos);

							dao.persist(anunciante);
							dao.flush();

							anuncio.setAnunciante(anunciante);

							dao.persist(anuncio);
							dao.flush();
						} else {
							
							anuncio.setNome("Quero tocar ocasionalmente");
							anuncio.setDescricao("Toco Banjo e Clarinete palavra chave '123' ");
							anuncio.setInteresse("Ocasionalmente");
							anuncio.setPalavraChave("123");

							
							anunciante.setNome("Nicolas");
							anunciante.setCidade("João Pessoa");
							anunciante.setBairro("Valentina");
							

							instrumentos.add(new Instrumentos("Clarinete"));
							instrumentos.add(new Instrumentos("Banjo"));
							anunciante.setListInstrumentos(instrumentos);

							gosta.add(new Style("Jazz"));
							gosta.add(new Style("Instrumental"));
							anuncio.setStylesLike(gosta);

							naoGosta.add(new Style("Folk"));
							naoGosta.add(new Style("Forró"));
							anuncio.setStylesNotLike(naoGosta);

							contatos.setEmail("nicolas@ccc.ufcg.edu.br");
							contatos.setOutrosContatos("http://www.facebook.com.br/nicolasgabriel");
							contatos.setTelefone("(83)9939-2665");
							dao.persist(contatos);
							dao.flush();
							anunciante.setContato(contatos);

							dao.persist(anunciante);
							dao.flush();

							anuncio.setAnunciante(anunciante);

							dao.persist(anuncio);
							dao.flush();
						}
					}
			
						for (int j = 0; j < 25; j++) {
							
						}
					}
			
			
			
			}});
		
		
		
	}

	public void onStop(Application app) {
		Logger.info("Aplicação desligada...");
	}
}
