//package unidade;
//
//import static org.fest.assertions.Assertions.assertThat;
//
//import java.util.List;
//
//import models.Anunciante;
//import models.Anuncio;
//import models.dao.GenericDAO;
//
//import org.junit.Test;
//
//import base.AbstractTest;
//
//public class LivroTest extends AbstractTest {
//
//    GenericDAO dao = new GenericDAO();
//	List<Anuncio> livros;
//	List<Anunciante> autores;
//	
//	@Test
//	public void deveSalvarLivroSemAutor () {
//		livros = dao.findAllByClass(Anuncio.class); //consulta o bd
//		assertThat(livros.size()).isEqualTo(0);
//		
//		Anuncio l1 = new Anuncio("Biblia Sagrada");
//		dao.persist(l1);
//		
//		livros = dao.findAllByClass(Anuncio.class); //consulta o bd
//		assertThat(livros.size()).isEqualTo(1);
//		assertThat(livros.get(0).getNome()).isEqualTo("Biblia Sagrada");
//	}
//	
//	@Test
//	public void deveSalvarLivroComAutor() {
//		Anunciante a1 = new Anunciante("George Martin");
//		Anuncio l1 = new Anuncio("A Game of Thrones", a1); // cria o livro com autor
//		a1.setAnuncio(l1); // add o livro ao autor
//		
//		dao.persist(l1); // salva tudo junto
//		
//		livros = dao.findAllByClass(Anuncio.class); // carrega os livros com seu autor
//		assertThat(livros.size()).isEqualTo(1);
//		assertThat(livros.get(0).getNome()).isEqualTo("A Game of Thrones");
//		assertThat(livros.get(0).getAutores().size()).isEqualTo(1);
//		
//		autores = dao.findAllByClass(Anunciante.class); // carrega os autores com seus livros
//		assertThat(autores.size()).isEqualTo(1);
//		assertThat(autores.get(0).getNome()).isEqualTo("George Martin");
//		assertThat(autores.get(0).getAnuncio().size()).isEqualTo(1);
//	}
//}
