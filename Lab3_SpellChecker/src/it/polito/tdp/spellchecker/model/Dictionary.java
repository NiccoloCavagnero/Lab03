package it.polito.tdp.spellchecker.model;
import java.util.*;
import java.io.*;

public class Dictionary {
	
	LinkedList<String> lista;
	
	public Dictionary() {
		lista = new LinkedList<String>();
	}
	
	public void loadDictionary(String language) {
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader("rsc/"+language+".txt"));
			String word;
		    
			while ( (word = reader.readLine()) != null ) {
				lista.add(word);
			}
			reader.close();
		} catch (IOException ioe) {
			System.out.println("Errore lettura file.");
		}
		
	}
	public List<RichWord> spellCheckText(List<String> imputTextList){
		
		List<RichWord> out = new LinkedList<RichWord>();
		
		for ( String s : imputTextList ) {
			if ( lista.contains(s) )
				out.add(new RichWord(s,true));
			else
			    out.add(new RichWord(s,false));
		}
		return out;
	}

}
