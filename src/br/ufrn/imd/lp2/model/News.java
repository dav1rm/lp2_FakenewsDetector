package br.ufrn.imd.lp2.model;

import java.util.ArrayList;

public class News extends Quote {
	private double newsAccuracy;	// taxa de veracidade da notícia
	private ArrayList <String> sources;	// lista com as referências aos sites em que encontrou-se resultados
	
	public double getNewsAccuracy() {	return newsAccuracy;	}
	public void setNewsAccuracy(double newsAccuracy) {	this.newsAccuracy = newsAccuracy;	}
}
