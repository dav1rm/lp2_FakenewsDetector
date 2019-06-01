package br.ufrn.imd.lp2.model;

import java.util.ArrayList;

/**
 * NO MODEL, ESTAMOS MANTENDO SOMENTE GETS E SETS
 * */
public class DataStorage {
	private ArrayList <Quote> dataStorage;

	public ArrayList <Quote> getDataStorage() {
		return dataStorage;
	}

	public void setDataStorage(ArrayList <Quote> dataStorage) {
		this.dataStorage = dataStorage;
	}
	
	public void addToDataStorage(Quote quote) 
	{
		this.dataStorage.add(quote);
	}
}
