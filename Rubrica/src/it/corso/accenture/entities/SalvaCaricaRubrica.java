package it.corso.accenture.entities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public abstract class SalvaCaricaRubrica {
	
	public static boolean salvaRubrica(String path, Rubrica rubrica) {
		
		File file = new File(path);

		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
			
			file.createNewFile();
			
			oos.writeObject(rubrica);

			return true;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
		
	}
	
	public static Rubrica caricaRubrica(String path) {
		
		File file = new File(path);

		if (!file.exists())
			return null;
 
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {

			return  (Rubrica) ois.readObject();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return null;
		
	}

}
