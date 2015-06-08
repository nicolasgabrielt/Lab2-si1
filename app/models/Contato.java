package models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity(name = "Contato")
public class Contato {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	private String telefone;
	@Column
	private String email;
	@Column
	private String outrosContatos;
		
	public Contato(){

	}
	
	public Contato(String telefone, String Email, String outrosContatos){
		this.telefone = telefone;
		this.email = email;
		this.outrosContatos = outrosContatos;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOutrosContatos() {
		return outrosContatos;
	}

	public void setOutrosContatos(String outrosContatos) {
		this.outrosContatos = outrosContatos;
	}
	
	
	
}
