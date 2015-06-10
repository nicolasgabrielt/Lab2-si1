import controllers.Application;
import models.Anuncio;
import models.Instrumentos;
import models.dao.GenericDAO;

import org.junit.*;

import play.db.jpa.JPA;
import play.db.jpa.JPAPlugin;
import play.mvc.Http;
import play.mvc.Result;
import play.test.FakeApplication;
import play.test.FakeRequest;
import play.test.Helpers;
import scala.Option;

import javax.persistence.EntityManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.*;


public class ApplicationControllerTest {
	Result result;
	public EntityManager em;

	private static FakeApplication app;
	private static final GenericDAO dao = new GenericDAO();;
	
	@BeforeClass
	public static void startApp() {
		app = Helpers.fakeApplication(new Global());
		Helpers.start(app);
	}

	@Before
	public void inicializacao() {
        Option<JPAPlugin> pluginJPA = app.getWrappedApplication().plugin(JPAPlugin.class);
        em = pluginJPA.get().em("default");
        JPA.bindForCurrentThread(em);
        em.getTransaction().begin();
	}
	
	@Test
	public void callIndex() {
		// realiza a chamada ao método index() do Application
		result = callAction(controllers.routes.ref.Application.index(),
				fakeRequest());
		// ao chamar o metodo index do Application, ele redireciona para '/books'
		assertThat(status(result)).isEqualTo(Http.Status.SEE_OTHER);
		assertThat(redirectLocation(result)).isEqualTo("/anuncios");
	}
	
	@Test
	public void callAnuncios() {
		// realiza a chamada ao método books() do Application
		result = callAction(controllers.routes.ref.Application.anuncios(),
				fakeRequest());
		// ao chamar o método index do Application, ele retora o html
		// correspondente.
		assertThat(status(result)).isEqualTo(Http.Status.OK);
		assertThat(charset(result)).isEqualTo("utf-8");
//		assertThat(contentAsString(result)).contains("<h3>10 Publicados<h3>");
//		assertThat(contentAsString(result)).contains("<h3>10 Pendentes<h3>");
//		assertThat(contentAsString(result)).contains("<h3>0 Resolvidos<h3>");
	}
	
	@Test
    public void TestDeCriarAnuncio() {
		Random random = new Random();
		List<Instrumentos> listInstrumento =  dao.findAllByClass(Instrumentos.class);
		
		String instrumento = String.valueOf(
					listInstrumento.get(random.nextInt(listInstrumento.size() - 1)).getId());
        
		Map<String, String> requestMap = new HashMap<>();

        requestMap.put("titulo", "Testando");
        requestMap.put("descricao", "Só uma descrição de teste...");
        requestMap.put("ondeTocar", "Em Banda");
        requestMap.put("palavrachave", "123");
       
        requestMap.put("nome", "Nicolas");
        requestMap.put("cidade", "São Bento");
        requestMap.put("bairro", "Centro");
        requestMap.put("instrumentos", instrumento);
       
        requestMap.put("email", "nicolas@gmail.com");
        requestMap.put("telefone", "8787-8787");
        requestMap.put("facebook", "http://www.facebook.com");

        FakeRequest fakeRequest = new FakeRequest().withFormUrlEncodedBody(requestMap);
		Result resultPost = callAction(controllers.routes.ref.Application.novoAnuncio(), fakeRequest);
		assertThat(status(resultPost)).isEqualTo(SEE_OTHER);
		assertThat(flash(resultPost).containsKey("erro"));
		
		Result resultGet = callAction(controllers.routes.ref.Application.anuncios(), new FakeRequest());
		assertThat(status(resultGet)).isEqualTo(OK);
		assertThat(contentType(resultGet)).isEqualTo("text/html");
		assertThat(contentAsString(resultGet)).contains("Só uma descrição de teste...");

	}
	
}