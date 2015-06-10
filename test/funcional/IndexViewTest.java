//package funcional;
//
//import static org.fest.assertions.Assertions.assertThat;
//import static play.test.Helpers.contentAsString;
//import static play.test.Helpers.contentType;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import models.Anuncio;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import play.data.Form;
//import play.mvc.Content;
//import views.html.index;
//
//public class IndexViewTest {
//
//	List<Anuncio> livros;
//	Anuncio livro1;
//
//	@Before
//	public void iniciaVariaveis() {
//		livros = new ArrayList<Anuncio>();
//		livro1 = new Anuncio("Calculo I");
//        livro1.setId(1L);
//	}
//
//	// Testa o template index.scala.html
//	@Test
//	public void indexTemplate() {
//		livros.add(livro1);
//
//		// guarda o html resultante da renderização do index.scala.html
//		// com a lista de livros e o formulario
//		Content html = index.render(livros);
//		assertThat(contentType(html)).isEqualTo("text/html");
//		// verifica se o html contém a determimnada string, no caso o nome do
//		// livro
//		assertThat(contentAsString(html)).contains(livro1.getNome());
//	}
//
//}
