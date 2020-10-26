package it.corso.accenture.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Rubrica implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Map<String, Contatto> contatti;

	public Rubrica() {
		super();
		contatti = new TreeMap<String, Contatto>();
	}

	public Map<String, Contatto> getContatti() {
		return contatti;
	}

	public void setContatti(Map<String, Contatto> contatti) {
		this.contatti = contatti;
	}

	public boolean insertContatto(Contatto contatto) {
		if (contatti.containsKey(contatto.getNome()))
			return false;

		contatti.put(contatto.getNome(), contatto);
		return true;
	}

	public boolean deleteContatto(String nome) {
		if (!contatti.containsKey(nome))
			return false;

		contatti.remove(nome);
		return true;
	}

	public boolean deleteContatto(Contatto contatto) {
		return deleteContatto(contatto.getNome());
	}

	public boolean updateContatto(Contatto contatto) {
		if (!contatti.containsKey(contatto.getNome()))
			return false;

		contatti.put(contatto.getNome(), contatto);
		return true;
	}

	public Contatto ricercaPerNome(String nome) {
		return contatti.get(nome);
	}

	public List<Contatto> ricercaPerNumero(String numero) {
		return contatti.values().stream().filter(c -> c.getNumero().equalsIgnoreCase(numero))
				.collect(Collectors.toList());
	}
}
