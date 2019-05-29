package br.ufrn.imd.lp2.domain;

import java.util.ArrayList;

public class News extends Quote {
	private double newsAccuracy;
	private ArrayList <String> sources;
	
	public double getNewsAccuracy() {	return newsAccuracy;	}
	public void setNewsAccuracy(double newsAccuracy) {	this.newsAccuracy = newsAccuracy;	}
}
