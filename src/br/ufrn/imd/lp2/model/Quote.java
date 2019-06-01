package br.ufrn.imd.lp2.model;

public abstract class Quote {
	private String quote;	//quote armazena o texto original pego no dataset
	private String hash;	//hash armazena a versão em hash do texto
	
	public String getQuote() {	return quote;	}
	public String getHash() {	return hash;	}
	
	public void setQuote(String text) {	this.quote = text;	}
	public void setHash(String hash) {	this.hash = hash;	}
}
