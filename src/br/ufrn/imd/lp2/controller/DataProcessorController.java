package br.ufrn.imd.lp2.controller;

public class DataProcessorController {
	private final int MIN_CHARACTERS;
	DataProcessorController(int min_char)
	{
		this.MIN_CHARACTERS = min_char;
	}
	
	/**
	 *	Função responsável por remover as palavras que tem menos caracteres que MIN_CHARACTERS
	 * @param quote contém o texto
	 * @return string filtrada
	 **/
	public String removeUnqualifiedWords(String quote) 
	{
		String [] splitedQuote = quote.split(" ");
		for(String word : splitedQuote) 
		{
			if(word.length() < this.MIN_CHARACTERS) 
			{
				//remove word
			}
		}
		return quote;
	}
	
	/**
	 *	Função responsável por remover os caracteres especiais, como (&$#@*) e também os acentos.
	 * @param quote contém o texto
	 * @return string sem caracteres especiais e sem acentos
	 **/
	public String removeSpecialCharacters(String quote) 
	{
		return quote;
	}
	
	/**
	 *	Função responsável por remover palavras repitidas
	 * @param quote contém o texto
	 * @return string em que cada palavra aparece uma única vez
	 **/
	public String removeRepeatedWords(String quote) 
	{
		return quote;
	}
	
	/**
	 *	Função responsável por estabelecer a ordem alfabética nas palavras dentro da string.
	 * @param quote contém o texto
	 * @return string ordenada
	 **/
	public String alphabeticalSort(String quote) 
	{
		return quote;
	}
	
	/**
	 *	Função responsável por gerar um hash único para cada string
	 * @param quote contém o texto
	 * @return string hash referente ao quote
	 **/
	public String generateHash(String quote) 
	{
		return "";
	}
}
