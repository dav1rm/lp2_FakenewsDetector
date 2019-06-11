package br.ufrn.imd.lp2.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import br.ufrn.imd.lp2.model.Quote;

public class FileReaderController {
	String filepath;
	private final String COMMA_DELIMITER = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";
	ArrayList <Quote> quotes = new ArrayList<Quote>(); 
	
	
	public FileReaderController(String filepath) throws FileNotFoundException, IOException {
		// TODO Auto-generated constructor stub
		this.filepath = filepath;
		// Lê o arquivo
		this.readFile();
		
	}
	
	/*
	 * Para cada linha do arquivo no filepath, cria um novo objeto quote com os valores
	 * da linha, caso a linha contenha todas as colunas texto, id, url e data*/
	public void readFile() throws FileNotFoundException, IOException {
		
		try (BufferedReader br = new BufferedReader(new FileReader(this.filepath))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		    	Quote quote = new Quote();
		        String[] values = line.split(COMMA_DELIMITER);
		        
		        if(values.length == 4) 	//SE A LINHA DO CSV TEM AS 4 COLUNAS
		        {
		        	quote.setId(values[0]);
			        quote.setContent(values[1]);
			        quote.setUrl(values[2]);
			        quote.setDate(values[3]);
			        quotes.add(quote);
		        }
		       
		    }
		}
	}

}
