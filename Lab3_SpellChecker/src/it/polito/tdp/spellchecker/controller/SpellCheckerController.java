package it.polito.tdp.spellchecker.controller;

import java.net.URL;
import java.util.*;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class SpellCheckerController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label time;

    @FXML
    private ComboBox<String> boxLingua;

    @FXML
    private TextArea txtIn;

    @FXML
    private Button btnSpell;

    @FXML
    private TextArea txtOut;

    @FXML
    private Label errors;

    @FXML
    private Button btnClear;
    
    private Dictionary model;
    
    public void setModel(Dictionary model) {
    	this.model = model;
    	boxLingua.getItems().addAll("English","Italian");
    	btnClear.setDisable(true);
    }

    @FXML
    void doClearText(ActionEvent event) {
         txtOut.clear();
         txtIn.clear();
    }
    

    @FXML
    void doSpellCheck(ActionEvent event) {
    	
    	double t1 = System.nanoTime();
    	
    	txtOut.clear();
    	
    	model.loadDictionary(boxLingua.getValue());
    	
    	List<String> lista = new LinkedList<String>();
    	
    	String input = txtIn.getText().replaceAll("[^a-zA-Z]"," ");
    	
    	String array[] = input.split(" ");
    	
    	for ( int i = 0; i<array.length; i++) 
    		if ( array[i].compareTo("") != 0 )
    			lista.add(array[i]);
    	
    	List<RichWord> risultato = model.spellCheckText(lista);
    	
    	List<String> output = new LinkedList<String>();
    	
    	for ( RichWord r: risultato ) 
    		if ( r.isCorretta() == false ) 
    			output.add(r.getParola());
    	
    	int count = 0;
    	
    	for ( String s : output ) {
    		txtOut.appendText(s+"\n");
    		count ++;
    	}
    	
    	errors.setText("The input text contains "+count+" errors");
    	
    	double t2 = System.nanoTime();
    	
    	btnClear.setDisable(false);
    	
    	time.setText("Process time : "+(t2-t1)+" seconds.");

    }
    

    @FXML
    void initialize() {
        assert time != null : "fx:id=\"time\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert boxLingua != null : "fx:id=\"boxLingua\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtIn != null : "fx:id=\"txtIn\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert btnSpell != null : "fx:id=\"btnSpell\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtOut != null : "fx:id=\"txtOut\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert errors != null : "fx:id=\"errors\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'SpellChecker.fxml'.";

    }
}
