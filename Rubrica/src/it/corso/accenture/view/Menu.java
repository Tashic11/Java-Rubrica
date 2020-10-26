package it.corso.accenture.view;

import java.util.List;
import java.util.Scanner;

import it.corso.accenture.entities.Contatto;
import it.corso.accenture.entities.Rubrica;
import it.corso.accenture.entities.SalvaCaricaRubrica;

public class Menu {

	private Scanner scanner;
	private Rubrica rubrica;
	private String fileName;

	public Menu() {
		scanner = new Scanner(System.in);
		
		fileName="contatti.bin";

		rubrica = SalvaCaricaRubrica.caricaRubrica(fileName);
		
		if(rubrica!=null)
			System.out.println("Contatti caricati.\n");
		else
			rubrica = new Rubrica();

	}

	public void mostra() {

		boolean esci = false;

		do {

			int scelta = sceltaOpzioni();

			switch (scelta) {
			case 1:
				visualizzaElenco();
				break;
			case 2:
				inserisciContatto();
				break;
			case 3:
				rimuoviContatto();
				break;
			case 4:
				modificaContatto();
				break;
			case 5:
				cercaPerNome();
				break;
			case 6:
				cercaPerNumero();
				break;
			case 7:
				esci();
				esci = true;
				break;
			}

			aspettaUtente();

		} while (!esci);

	}

	public int sceltaOpzioni() {
		System.out.println("--Effettua operazioni sui contatti della rubrica--");
		System.out.println(
				"Numero contatti salvati : " + rubrica.getContatti().size());

		int scelta = 0;

		do {
			System.out.println();
			System.out.println("Scegli un opzione:");

			System.out.println(""
					+ "1)Visualizza elenco contatti,\n"
					+ "2)Inserisci nuovo contatto,\n"
					+ "3)Rimuovi contatto,\n"
					+ "4)Modifica contatto,\n"
					+ "5)cerca contatto per nome,\n"
					+ "6)cerca contatti per numero,\n"
					+ "7)Esci.");

			try {
				scelta = scanner.nextInt();
			} catch (Exception e) {

			}
			scanner.nextLine();

		} while (scelta < 1 || scelta > 7);

		return scelta;
	}

	public void visualizzaElenco() {
		System.out.println();

		if (rubrica.getContatti().size() > 0) {
			System.out.println("I contatti salvati nella rubrica sono:");
			for (Contatto c : rubrica.getContatti().values())
				System.out.println(c);
		} else
			System.out.println("Non è stato inserito nessun contatto nella rubrica.");

		System.out.println();
	}

	public void inserisciContatto() {
		System.out.println();

		String nome = chiediStringa("Inserisci il nome del contatto.");

		String numero = chiediStringa("Inserisci il numero del contatto.");

		Contatto nuovo = new Contatto(nome, numero);

		System.out.println();

		if (rubrica.insertContatto(nuovo))
			System.out.println("Il contatto è stato inserito nella rubrica.");
		else
			System.out.println("Inserimento non effettuato. Il contatto è già presente nella rubrica.");

		System.out.println();
		
		salvaRubrica();
	}

	public void rimuoviContatto() {
		System.out.println();

		String nome = chiediStringa("Inserisci il nome del contatto.");

		System.out.println();

		if (rubrica.deleteContatto(nome))
			System.out.println("Il contatto è stato rimosso nella rubrica.");
		else
			System.out.println("Rimozione non effettuata. Il contatto non è presente nella rubrica.");

		System.out.println();
		
		salvaRubrica();
	}

	public void modificaContatto() {
		System.out.println();

		String nome = chiediStringa("Inserisci il nome del contatto.");

		System.out.println();

		Contatto trovato = rubrica.ricercaPerNome(nome);

		if (trovato == null) {
			System.out.println("Modifica non effettuata. Il contatto non è presente nella rubrica");
			System.out.println();
			return;
		}

		trovato.setNumero(chiediStringa("Inserisci il nuovo numero del contatto."));

		rubrica.updateContatto(trovato);

		System.out.println("Il contatto è stato modificato.");

		System.out.println();
		
		salvaRubrica();
	}

	public void cercaPerNome() {

		System.out.println();

		String nome = chiediStringa("Inserisci il nome del contatto.");

		System.out.println();

		Contatto trovato = rubrica.ricercaPerNome(nome);

		if (trovato != null)
			System.out.println("Il numero del contatto di nome " + nome + " è " + trovato.getNumero() + ".");
		else
			System.out.println("Contatto non trovato. Il contatto non è presente nella rubrica.");

		System.out.println();
	}

	public void cercaPerNumero() {

		System.out.println();

		String numero = chiediStringa("Inserisci il numero dei contatti da trovare.");

		System.out.println();

		List<Contatto> trovati = rubrica.ricercaPerNumero(numero);

		if (trovati.size() > 0) {
			System.out.println("I contatti trovati col numero richiesto sono:");
			for (Contatto c : trovati)
				System.out.println(c);
		} else
			System.out.println("Contatti non trovati. Non sono presenti contatti con il numero richiesto.");

		System.out.println();
	}

	public void esci() {
		System.out.println();

		System.out.println("Fine Programma");

		System.out.println();
	}

	public String chiediStringa(String messaggio) {
		System.out.println(messaggio);

		String parola = scanner.nextLine();

		return parola;
	}

	public int chiediIntPos(String messaggio) {

		int scelta = 0;

		do {
			System.out.println(messaggio);
			try {
				scelta = scanner.nextInt();
			} catch (Exception e) {

			}
			scanner.nextLine();

		} while (scelta < 0);

		return scelta;
	}

	public int chiediIntRange(String messaggio, int min, int max) {

		int scelta = 0;

		do {
			System.out.println(messaggio);
			try {
				scelta = scanner.nextInt();
			} catch (Exception e) {

			}
			scanner.nextLine();

		} while (scelta < min || scelta > max);

		return scelta;
	}

	public void aspettaUtente() {
		System.out.println("Premi invio per continuare...");
		scanner.nextLine();
	}
	
	public void salvaRubrica() {
		if(SalvaCaricaRubrica.salvaRubrica(fileName, rubrica))
			System.out.println("Rubrica salvata");
		else
			System.out.println("Non è stato possibile salvare le modifiche.");
	}

	public void close() {
		scanner.close();
	}

}