package it.polito.tdp.lab04.model;

import java.util.List;

public class TestModel {

	public static void main(String[] args) {

		Model model = new Model();
		
		/*
		 * 	Write here your test model
		 */
		System.out.println("ciao");
		int i= 155459;
		Studente s= model.getNomeCognome(i);
		System.out.println(s.getNome()+" "+s.getCognome()+"\n");
		
		List<Studente> l= model.getStudentiByCorso("Gestione dell'innovazione e sviluppo prodotto");
		
		for(Studente si :l) {
			System.out.println(si.getMatricola()+" "+si.getCognome()+"\n");
		}
		
		int j=146101;
		Studente sss= model.getS(j);
		System.out.println(sss.getCognome());

	}

	
	
}
