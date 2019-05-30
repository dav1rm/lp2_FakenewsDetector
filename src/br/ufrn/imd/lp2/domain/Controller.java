package br.ufrn.imd.lp2.domain;

import java.util.ArrayList;

public class Controller {
	private final int MIN_CHARACTERS = 3;
	private final double MIN_ACCURACY = 0.6;
	
	private ArrayList <Quote> quotesSet;
	Controller(ArrayList <String> dataset)
	{
		//se dataset estiver vazio, throw exception
		WebScrapper ws = new WebScrapper();
		
		for(String quote : dataset)
		{
			ArrayList <Exception> errs = null;		// Caso a quote não seja Fakenews, será ignorado
			
			String standQuote = standardizeQuote(quote);	//adicionar throw exception de erro na string caso vazia
			
			double quoteAccuracy = ws.measureAccuracy(standQuote);		//rodar o web scraping em cima dessa standQuote
			
			if(quoteAccuracy < this.MIN_ACCURACY) 
			{
				Fakenews fn = new Fakenews();
				fn.setQuote(quote);
				//fn.setHash(hash);
				fn.setErrs(errs);
			}else 
			{
				News n = new News();
				n.setQuote(quote);
				//n.setHash(hash);
				n.setNewsAccuracy(quoteAccuracy);
				//n.setSource();
				
			}
			// webscraping vai retornar a accuracy e as fontes
			// o webscraping vai lançar excecoes 
			
			
		}
	}
	
	/**
	 *	Função responsável por padronizar a string recebida 
	 * @param quote contém o texto
	 * @return string no padrão lowercase, com words maiores que MIN_CHARACTERS e em ordem alfabética
	 **/
	public String standardizeQuote(String quote) 
	{
		// lowercase
		// retirar palavras com menos de MIN_CHARACTERS char
		// por em ordem alfabética
		return quote;
	}
}
