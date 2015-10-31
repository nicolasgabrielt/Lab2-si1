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
public class AnuncioDeletado extends Anuncio implements Comparable<Anuncio>{

	public AnuncioDeletado(){
		super();
	}
	
	public AnuncioDeletado(String nome, Anunciante anunciante,String descricao,List<Style> stylesLike,
    		List<Style> stylesNotLike , String interesse){
		super(nome,anunciante,descricao,stylesLike,
	    		stylesNotLike , interesse);
	}
}
