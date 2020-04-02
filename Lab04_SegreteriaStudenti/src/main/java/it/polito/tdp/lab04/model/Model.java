package it.polito.tdp.lab04.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	
	private CorsoDAO c;
	private StudenteDAO st;
	
	public Model() {
		c= new CorsoDAO();
		st = new StudenteDAO();
	}
	
	public List<Corso> getTuttiICorsi(){
		
		return c.getTuttiICorsi();
	}

	public Studente getNomeCognome(int i) {
		List<Studente> studenti= st.getTuttiStudenti();
		for(Studente s: studenti) {
			if(s.getMatricola()==i) {
				return s;
			}
		}
		return null;
	}

	public List<Studente> getStudentiByCorso(String s) {
		// TODO Auto-generated method stub
		Corso corso= c.getCorso(s);
		List<Studente> studenti= c.getStudentiIscrittiAlCorso(corso);
		return studenti;
	}
	
	public List<Corso> getCorsiByStudente(int matricola){
		Studente s= this.st.getStudente(matricola);
		List<Corso> c= st.getCorsiByStudente(s);
		return c;
	}
	
	public boolean cercaStudentePerCorso(int matricola, String nomeCorso) {
		
		Corso c= this.c.getCorso(nomeCorso);
		Studente s= this.st.getStudente(matricola);
		
		return this.c.cercaStudentePerCorso(s, c);
	}
	
	public Studente getS(int i) {
		return st.getStudente(i);
	}

	public boolean inscriviStudenteACorso(int matricola, String nomeCorso) {
		Corso c= this.c.getCorso(nomeCorso);
		Studente s= this.st.getStudente(matricola);
		boolean b= this.c.inscriviStudenteACorso(s, c);  //nella funzione del model che passo al dao devo passare oggetti
		return b;
	}
	

}
