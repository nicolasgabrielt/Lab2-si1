package controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.persistence.Query;

import models.Anunciante;
import models.Anuncio;
import models.Contato;
import models.Instrumentos;
import models.Style;
import models.dao.GenericDAO;
import play.Logger;
import play.data.DynamicForm;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.Map;
/**
 * Controlador Principal do Sistema
 */
public class Application extends Controller {
	private static Form<Anuncio> bookForm = Form.form(Anuncio.class);
	private static final GenericDAO dao = new GenericDAO();
	private static int anunciosResolvidos = 0;
	
	 private static int SEM_ERRO = 0;
	 private static int COM_ERRO = 1;
	 private static int DELETOU = 2;
	
	 private static int isErro = SEM_ERRO;
	
	 public static Result index(){
	    return redirect(routes.Application.anuncios());
	}
	
	@Transactional
	public static Result newAnuncio(){
	  List<Style> styles = dao.findAllByClass(Style.class);
	  List<Instrumentos> instrumentos = dao.findAllByClass(Instrumentos.class);
		return ok(views.html.newAnuncio.render(styles,instrumentos));
	}

	/*
	 * A Anotação transactional é necessária em todas as Actions que
	 * usarem o BD.
	 */
	
	@Transactional
	public static Result anuncios() {
		int erro = isErro;
		isErro = SEM_ERRO;
		 //Todos os Livros do Banco de Dados
		List<Anuncio> result = dao.findAllByClass(Anuncio.class);
		Collections.sort(result);
		return ok(views.html.index.render(result,anunciosResolvidos,erro));
	}

	
	@Transactional 
	public static Result novoAnuncio(){
		
		Anuncio anuncio = new Anuncio();
		Anunciante anunciante = new Anunciante();
		Contato contatos = new Contato();
		
		DynamicForm requestAnuncio = Form.form().bindFromRequest();
				
		
		try {
			/*
			 * Dados do anucio
			 */
			anuncio.setNome(requestAnuncio.get("titulo"));
			anuncio.setDescricao(requestAnuncio.get("descricao"));
			anuncio.setStylesLike(getStylesSelectedData("estilosQueGosta"));
			anuncio.setStylesNotLike(getStylesSelectedData("estilosQueNaoGosta"));
			anuncio.setInteresse(requestAnuncio.get("ondeTocar"));
			anuncio.setPalavrachave(requestAnuncio.get("palavrachave"));
		
			/*
			 * Dados do anunciante
			 */
			anunciante.setNome(requestAnuncio.get("nome"));
			anunciante.setBairro(requestAnuncio.get("bairro"));
			anunciante.setCidade(requestAnuncio.get("cidade"));
			anunciante.setInstrumentos(getInstrumentSelectedData());
			

			contatos.setEmail(requestAnuncio.get("email"));
			contatos.setOutrosContatos(requestAnuncio.get("facebook"));
			contatos.setTelefone(requestAnuncio.get("telefone"));

			/*
			 * Atualiza dados do contato no banco de dados
			 */
			dao.persist(contatos);
			dao.flush();
			anunciante.setContato(contatos);
			/*
			 * Atualiza dados do anunciante no banco de dados
			 */
			dao.persist(anunciante);
			dao.flush();
			anuncio.setAnunciante(anunciante);
			/*
			 * Atualiza dados do anuncio no banco de dados
			 */
			dao.persist(anuncio);
			dao.flush();
		} catch (Exception e) {
			e.getMessage();
		}
						return redirect(routes.Application.index());	
				
	}

	@Transactional
	public static Result deleteAnuncio(Long id) {
		DynamicForm requestAnuncio = Form.form().bindFromRequest();
		
		if(requestAnuncio.get("palavrachave").equals((dao.findByEntityId(Anuncio.class, id)).getPalavrachave())){
			
		
		// Remove o Livro pelo Id
		dao.removeById(Anuncio.class, id);
		// Espelha no banco de dados
		dao.flush();
		anunciosResolvidos++;
		isErro = DELETOU;
			return redirect(routes.Application.anuncios());
		}
		
		isErro = COM_ERRO;
		return redirect(routes.Application.anuncios());
		
	}
	
	
	@Transactional
	private static List<Instrumentos> getInstrumentSelectedData() {
		Map<String, String[]> map = request().body().asFormUrlEncoded();
		
		Logger.debug(Arrays.asList(map.values()).toString());

		String[] checkedInstrumentos = map.get("instrumentos");
		List<Instrumentos> listInstrumentos = new ArrayList<>();

		if (checkedInstrumentos != null) {
			List<String> listaIdInstrumentos = Arrays
					.asList(checkedInstrumentos);
			for (String id : listaIdInstrumentos) {
				Long idInstrumento = Long.parseLong(id);
				Instrumentos instrumento = dao.findByEntityId(Instrumentos.class,
						idInstrumento);
				if (instrumento != null) {
					listInstrumentos.add(instrumento);
				}
			}
		}

		return listInstrumentos;
	}
	
	
	@Transactional
	private static List<Style> getStylesSelectedData(String name){
		Map<String, String[]> map = request().body().asFormUrlEncoded();
		
		String[] stylesMarcados = map.get(name);
		List<Style> styles = new ArrayList<>();
		
		if(stylesMarcados != null){
			List<String> listaIDstyles = Arrays.asList(stylesMarcados);
			
			for (String id: listaIDstyles) {
				Long idStyle = Long.parseLong(id);
				
				Style style = dao.findByEntityId(Style.class, idStyle);
				
				if(style != null){
				    styles.add(style);
				}
				
			}
		}
		Logger.info("LISTA: " + Arrays.toString(styles.toArray())); 
		return styles;
		
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public static Result pesquisarAnuncio(){
		DynamicForm requestPesquisa = Form.form().bindFromRequest();
		// Radio Button
		if(requestPesquisa.get("pesquisa").equals("Em Banda") || requestPesquisa.get("pesquisa").equals("Ocasionalmente")){
			Query consultaEmBanda =	dao.createQuery("SELECT a FROM Anuncio a WHERE a.interesse = :parametro)");
			
			consultaEmBanda.setParameter("parametro", requestPesquisa.get("pesquisa"));
			
			System.out.println(Arrays.asList(consultaEmBanda.getResultList()));
			return ok(views.html.index.render(consultaEmBanda.getResultList(),anunciosResolvidos
					,SEM_ERRO));
			// radio button
		}
					
		return redirect(routes.Application.index());
	}
	


}
