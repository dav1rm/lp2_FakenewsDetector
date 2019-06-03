package br.ufrn.imd.lp2.model;

import java.util.HashMap;

public class DataStorage {

	private HashMap<String, Quote> storage = new HashMap<String, Quote>(); 

	public HashMap<String, Quote> getStorage() {
		return storage;
	}

	public void setDataStorage(HashMap<String, Quote> storage) {
		this.storage = storage;
	}
	public void addToDataStorage(Quote quote, String hash) {
		this.storage.put(hash, quote);
	}
}
