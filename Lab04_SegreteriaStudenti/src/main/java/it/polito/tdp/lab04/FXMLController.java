package it.polito.tdp.lab04;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class FXMLController {
	
	
	@FXML
	private Model model;
	

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<String> btnChoice;

    @FXML
    private Button btnCercaIscritti;

    @FXML
    private TextField txtMatricola;

    @FXML
    private Button btnVerde;

    @FXML
    private Button btnCercaCorsi;

    @FXML
    private Button btnIscrivi;
    
    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCognome;

    @FXML
    private TextArea txtResult;

    @FXML
    private Button btnReset;

    @FXML
    void doCercaCorsi(ActionEvent event) {
    	
    	if(btnChoice.getValue().compareTo("")==0) {
    		int i=0;
    		try {
    			i= Integer.parseInt(txtMatricola.getText());
    		}catch(Exception e) {
    			System.out.println("Devi inserire un numero");
    			return;
    		}
        	List<Corso> corsi= model.getCorsiByStudente(i);
        	
        	if(corsi==null) {
        		txtResult.setText("Matricola inesistente");
        		return;
        	}
        	
        	StringBuilder sb = new StringBuilder();

			for (Corso corso : corsi) {
				sb.append(String.format("%-8s ", corso.getCodins()));
				sb.append(String.format("%-4s ", corso.getCrediti()));
				sb.append(String.format("%-45s ", corso.getNome()));
				sb.append(String.format("%-4s ", corso.getPd()));
				sb.append("\n");
			}
			txtResult.appendText(sb.toString());
    	}else {
    		int i=0;
    		try {
    			i= Integer.parseInt(txtMatricola.getText());
    		}catch(Exception e) {
    			System.out.println("Devi inserire un numero");
    			return ;
    		}
    		String s= this.btnChoice.getValue();
    		boolean b= model.cercaStudentePerCorso(i, s);
    		if(b==true) {
    			txtResult.setText("Studente già iscritto");
    		}else {
    			txtResult.setText("Studente non ancora iscritto");
    			btnIscrivi.setDisable(false);
    		}
    	}
    			
    }

    @FXML
    void doCercaIscritti(ActionEvent event) {
    	
    	String s= this.btnChoice.getValue();
    	
    	if(this.btnChoice.getValue()==null){
    		this.txtResult.setText("Scegliere un corso");
    		return;
    	}
    	if(s.compareTo("")==0) {
    		this.txtResult.setText("Scegliere un corso");
    		return;
    	}
    	
    	List<Studente> studenti = this.model.getStudentiByCorso(s);
    	StringBuilder sb = new StringBuilder();

		for (Studente studente : studenti) {

			sb.append(String.format("%-20s ", studente.getMatricola()));
			sb.append(String.format("%-20s ", studente.getCognome()));
			sb.append(String.format("%-20s ", studente.getNome()));
			sb.append(String.format("%-20s ", studente.getCds()));
			sb.append("\n");
		}

		txtResult.appendText(sb.toString());

    	
    }

    @FXML
    void doIscrizione(ActionEvent event) {
    	int i= Integer.parseInt(txtMatricola.getText());
		String s= this.btnChoice.getValue();
		boolean b= model.inscriviStudenteACorso(i, s);
		if(b=true) {
			txtResult.appendText("\n" +"Iscrizione avvenuta con successo");
		}else {
			txtResult.appendText("\n"+"Iscrizione non avvenuta con successo");
		}

    }

    @FXML
    void doMatching(ActionEvent event) {
    	
    	int i=0;
    	try {
    		i= Integer.parseInt(txtMatricola.getText());
    	}catch(Exception e) {
    		txtResult.setText("Inserire un numero");
    		return;
    	}
    	
    	Studente studente= model.getNomeCognome(i);
    	if(studente ==null) {
    		txtResult.setText("Studente non trovato");
    		return;
    	}
    	txtNome.setText(studente.getNome());
    	txtCognome.setText(studente.getCognome());

    }

    @FXML
    void doReset(ActionEvent event) {
    	
    	txtResult.clear();
    	txtMatricola.clear();
    	txtNome.clear();
    	txtCognome.clear();

    }

    @FXML
    void initialize() {
        assert btnChoice != null : "fx:id=\"btnChoice\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaIscritti != null : "fx:id=\"btnCercaIscritti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnVerde != null : "fx:id=\"btnVerde\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";
    }
    
    public void setModel(Model m) {
    	this.model=m;
    	btnChoice.getItems().add("");
    	for(Corso c: model.getTuttiICorsi()) {
    		btnChoice.getItems().add(c.getNome());
    	}
    	
    	this.btnChoice.setValue("");
    }
}

