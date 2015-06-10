package models;

import com.google.common.base.Objects;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Entidade que representa uma Tabela no Banco de Dados
@Entity(name="Anunciante")
public class Anunciante {

	// Gerador de Sequencia para o Id
	@Id
	@GeneratedValue
	private Long id;

	// Nome do Autor dos Livros
	private String nome;

	@Column
	private String Bairro;
	
	@Column
	private String Cidade;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Contato contato;
	
	@ManyToMany (cascade = CascadeType.ALL)
	private List<Instrumentos> instrumentos;
	
	// Construtor Vazio para o Hibernate criar os objetos
	public Anunciante() {
		
	}

    public Anunciante(String nome) {
        this();
        this.nome = nome;
    }
    
    public Anunciante (String nome,String bairro,String cidade,Contato contato,
    		List<Instrumentos> instrumentosQueToco){
    	this.nome = nome;
    	this.Bairro = bairro;
    	this.Cidade = cidade;
    	this.contato = contato;
    	this.instrumentos = instrumentosQueToco;
    }
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
    
    public String getNome() {
		return nome;
	}
	
	public void setNome(String nome){
		this.nome = nome;
	}

	public String getBairro() {
		return Bairro;
	}

	public void setBairro(String bairro) {
		Bairro = bairro;
	}

	public String getCidade() {
		return Cidade;
	}

	public void setCidade(String cidade) {
		Cidade = cidade;
	}

	public Contato getContato() {
		return contato;
	}

	public List<Instrumentos> getInstrumentos() {
		return instrumentos;
	}

	public void setListInstrumentos(List<Instrumentos> listInstrumentos)
			throws Exception {
		if (listInstrumentos.isEmpty()) {
			throw new Exception("Insira pelo menos um instrumento a sua lista.");
		}
		this.instrumentos = listInstrumentos;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Anunciante)) {
			return false;
		}
		Anunciante anunciante = (Anunciante) obj;
		return Objects.equal(anunciante.getNome(), this.getNome());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(this.nome);
	}
	
	public void setContato(Contato contato) throws Exception {
		if (contato.getEmail() == null && contato.getOutrosContatos() == null
				&& contato.getTelefone() == null
				|| contato.getEmail().trim().equals("")
				&& contato.getOutrosContatos().trim().equals("")
				&& contato.getTelefone().trim().equals("")) {
			throw new Exception("Insira pelo menos uma forma de contato.");

		}
		this.contato = contato;
	}

}
