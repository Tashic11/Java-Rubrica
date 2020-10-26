package it.corso.accenture.entities;

import java.io.Serializable;
import java.util.Objects;

public class Contatto implements Comparable<Contatto> ,Serializable {

	private static final long serialVersionUID = 1L;
	
	private String nome;
	private String numero;

	public Contatto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Contatto(String nome, String numero) {
		super();
		this.nome = nome.toLowerCase();
		this.numero = numero.toLowerCase();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome.toLowerCase();
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero.toLowerCase();
	}

	@Override
	public int hashCode() {
		return Objects.hash(nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Contatto))
			return false;
		Contatto other = (Contatto) obj;
		return nome.equalsIgnoreCase(other.nome);
	}
	
	

	@Override
	public String toString() {
		return "Nome : " + nome + ", Numero : " + numero;
	}

	@Override
	public int compareTo(Contatto arg0) {
		return nome.compareToIgnoreCase(arg0.getNome());
	}
	
	

}
