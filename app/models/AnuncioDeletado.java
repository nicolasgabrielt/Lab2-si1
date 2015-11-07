package models;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.google.common.base.Objects;

// Entidade que representa um Livro
// Referenciar a uma tabela
@Entity(name = "AnuncioDeletado")
public class AnuncioDeletado {

	// Todo Id tem que ter o GeneratedValue a não ser que ele seja setado
	// Usar Id sempre Long
	@Id
	@GeneratedValue
	private Long id;


    @Column
	private String nome;
	@Column
	private String descricao;

	@Column
	private String interesse;

	public AnuncioDeletado() {
	}
    
    public AnuncioDeletado(String nome, Anunciante anunciante,String descricao,String interesse) {
        
    
        this.nome = nome;
        this.descricao = descricao;
    }

	public Long getId() {
		return id;
	}
    
    public void setId(long id) {
        this.id = id;
    }
    

    public String getNome() {
		return nome;
	}

    public void setNome(String titulo) throws Exception {
		if (titulo.trim().equals("") || titulo == null) {
			throw new Exception("Insira um titulo ao anuncio");
		}
		this.nome = titulo;
	}
    
	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) throws Exception {
		if (descricao.trim().equals("") || descricao == null) {
			throw new Exception("Insira um descriçao ao anuncio.");
		}
		if (descricao.length() < 5) {
			throw new Exception(
					"Insira uma descrisao de no minimo 5 caracters.");
		}
		this.descricao = descricao;
	}
	
	public String getInteresse() {
		return interesse;
	}


	public void setInteresse(String interesse) throws Exception {
		if(interesse.trim().equals("")){
			throw new Exception("Insira onde voce quer tocar.");
		}
		this.interesse = interesse;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof AnuncioDeletado)) {
			return false;
		}
		Anuncio outroAnuncio = (Anuncio) obj;
		return Objects.equal(outroAnuncio.getNome(), this.getNome());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(this.getNome());
	}
}

