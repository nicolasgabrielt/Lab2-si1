package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="NumeroAnunciosResolvidos")
public class NumeroAnunciosResolvidos {
	
	@Column
	private int numeroDeAnunciosResolvidos;
	
	@Id
	@GeneratedValue
	private Long id;
	
	
	public NumeroAnunciosResolvidos(){
		
	}
	
	public int getNumeroDeAnunciosResolvidos() {
		return numeroDeAnunciosResolvidos;
	}

	public void setNumeroDeAnunciosResolvidos(int numeroDeAnunciosResolvidos) {
		this.numeroDeAnunciosResolvidos = numeroDeAnunciosResolvidos;
	}
	
	
}
